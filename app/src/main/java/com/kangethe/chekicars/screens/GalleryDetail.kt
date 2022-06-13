package com.kangethe.chekicars.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import com.kangethe.chekicars.R
import com.kangethe.chekicars.navigation.NavRoutes
import com.kangethe.myautocheckapi.models.CarMedia
import com.kangethe.myautocheckapi.models.CarMediaDetailResponse
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import com.kangethe.myautocheckapi.repository.MyAutoCheckAPI
import com.skydoves.landscapist.coil.CoilImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun GalleryDetails(
    myAutoCheckAPI: MyAutoCheckAPI,
    carId: String?,
    navController: NavHostController
) {

    val carMediaDetailsGallery by getCarDetailGallery(
        myAutoCheckAPI = myAutoCheckAPI,
        carId = carId,
        pageSize = 50,
        page = 1
    )

    if (carMediaDetailsGallery.isOk) {
        Log.e("carMedia", carMediaDetailsGallery.data.toString())
        Column {
            TopCarDetail(navController)
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween,
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 70.dp
                )
            ) {
                items(
                    items = carMediaDetailsGallery.data?.carMediaList!!,
                    itemContent = { item: CarMedia ->
                        displayMedia(item, navController)
                    })
            }
        }
    }
}

@Composable
fun getCarDetailGallery(
    myAutoCheckAPI: MyAutoCheckAPI?,
    carId: String?,
    pageSize: Int,
    page: Int
): State<MyAutoCheckResponse<CarMediaDetailResponse>> {
    return produceState(initialValue = MyAutoCheckResponse<CarMediaDetailResponse>()) {
        val response = myAutoCheckAPI?.getCarDetailsMedia(carId, pageSize, page)
        if (response != null) {
            value = response
        }
    }
}


@Composable
@Preview
fun displayMedia(carMedia: CarMedia? = null, navController: NavHostController? = null) {

    if (carMedia?.type?.split("/")?.first() == "image") {
        displayImage(carMedia)
    }

    if (carMedia?.type?.split("/")?.first() == "video") {
        Box(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            displayVideoThumbnail(carMedia)
            Icon(imageVector = Icons.Outlined.PlayArrow,
                contentDescription = null, modifier = Modifier
                    .size(36.dp)
                    .align(
                        Alignment.Center
                    )
                    .clickable {
                        val encodedUrl = URLEncoder.encode(carMedia.url, StandardCharsets.UTF_8.toString())
                    navController?.navigate("home/gallery/video/${encodedUrl}")

                    }
                , tint = Color.White
            )
            Text(
                text = carMedia.name!!,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}

@Composable
@Preview
fun displayImage(
    carMedia: CarMedia? = null
) {
    Box(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(carMedia?.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_baseline_broken_image_24),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text = carMedia?.name!!,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun displayVideoThumbnail(carMedia: CarMedia? = null) {

    val context = LocalContext.current
    val painter = // optionally set frame location
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = carMedia?.url)
                .apply(block = fun ImageRequest.Builder.() {
                    // optionally set frame location
                    videoFrameMillis(200)
                    crossfade(true)
                }).build()
        )

    val imageLoader = imageLoader(context = context)
        CoilImage(
            imageRequest = painter.request,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            imageLoader = { imageLoader },
            contentDescription = null
        )
}

@Composable
fun imageLoader(context: Context): ImageLoader {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(VideoFrameDecoder.Factory())
        }.build()
    return imageLoader
}