package com.kangethe.chekicars.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kangethe.chekicars.R
import com.kangethe.chekicars.ui.theme.ChekiCarsTheme
import com.kangethe.myautocheckapi.models.MakesListResponse
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import com.kangethe.myautocheckapi.repository.MyAutoCheckAPI
import com.skydoves.landscapist.glide.GlideImage
import retrofit2.Response.error

@Composable
fun Home(myAutoCheckAPI: MyAutoCheckAPI) {
    val textState = remember{ mutableStateOf(TextFieldValue(""))}
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        SearchView(state = textState)
        val makestate by getPopularMakes(myAutoCheckAPI = myAutoCheckAPI)
        if(makestate.isOk){
            Log.e("makes", makestate.data?.toString()!!)
            LazyRow(modifier = Modifier.fillMaxWidth()){
                items(items = makestate.data!!.makeList!!, itemContent = {item ->

                    circularIcon(item.imageUrl,
                                    item.name)
                })
            }
        }
    }
}

@Composable
fun getPopularMakes(
    myAutoCheckAPI: MyAutoCheckAPI?
): State<MyAutoCheckResponse<MakesListResponse>> {
    return produceState(initialValue = MyAutoCheckResponse<MakesListResponse>()) {
        val response =
            myAutoCheckAPI?.getPopularMakes()
        if (response != null) {
            value = response
        }
    }
}

@Composable
fun circularIcon(
    imageUrl:String? = null,
    makeName:String? = null
){
    Column() {
        GlideImage(
            imageModel = imageUrl,
            modifier = Modifier.size(120.dp,120.dp),
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Inside,
            // shows a placeholder while loading the image.
            placeHolder = painterResource(id = R.drawable.ic_baseline_image_24),
            // shows an error ImageBitmap when the request failed.
            error = painterResource(id = R.drawable.ic_baseline_broken_image_24)
        )

        Text(text = makeName!! )
    }
}

@Composable
@Preview
fun circularPreview(){
    ChekiCarsTheme {
        circularIcon(
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/marketplace/audi.png",
            makeName = "Audi"
        )
    }
}