package com.kangethe.chekicars.screens

import android.util.Log
import android.util.Log.d
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kangethe.chekicars.R
import com.kangethe.chekicars.ui.theme.ChekiCarsTheme
import com.kangethe.myautocheckapi.models.CarListResponse
import com.kangethe.myautocheckapi.models.MakesListResponse
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import com.kangethe.myautocheckapi.repository.MyAutoCheckAPI
import com.skydoves.landscapist.coil.CoilImage
import kotlin.math.roundToInt


@Composable
fun Home(myAutoCheckAPI: MyAutoCheckAPI, navController: NavHostController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchView(state = textState)
        val makestate by getPopularMakes(myAutoCheckAPI = myAutoCheckAPI)
        if (makestate.isOk) {
            Log.e("makes", makestate.data?.toString()!!)
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(items = makestate.data!!.makeList!!, itemContent = { item ->
                    circularIcon(
                        item.imageUrl,
                        item.name
                    )
                })
            }
        }

        val carListState by getCars(myAutoCheckAPI = myAutoCheckAPI, pageSize = 50, page = 2 )
        if(carListState.isOk){
            Log.e("carList",carListState.data.toString())
            LazyColumn{
                items(items = carListState.data?.result!!, itemContent = { carItem ->
                    CarCard(
                        imageUrl = carItem.imageUrl,
                        yearOfManufacture = carItem.year.toString(),
                        carName = carItem.title,
                        carCostNow = carItem.marketplacePrice.toString(),
                        carCostBefore = carItem.marketplaceOldPrice.toString(),
                        gradeScore = carItem.gradeScore?.roundToInt().toString(),
                        mileage = carItem.mileage.toString(),
                        mileageUnits = carItem.mileageUnit,
                        location = carItem.city,
                        carState = carItem.sellingCondition,
                        carId = carItem.id!!,
                        navController = navController
                    )
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
fun getCars(
    myAutoCheckAPI: MyAutoCheckAPI?,
    pageSize:Int,
    page:Int
): State<MyAutoCheckResponse<CarListResponse>> {
    return produceState(initialValue = MyAutoCheckResponse<CarListResponse>()){
        val response = myAutoCheckAPI?.getCarsList(pageSize, page)
        if(response != null){
            value = response
        }
    }
}

@Composable
fun circularIcon(
    imageUrl: String? = null,
    makeName: String? = null
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            placeholder = painterResource(id = R.drawable.ic_baseline_image_24),
            error = painterResource(id = R.drawable.ic_baseline_broken_image_24),
            contentDescription = "make image",
            modifier = Modifier
                .size(120.dp, 120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primaryVariant),
            contentScale = ContentScale.Inside
        )

        Text(text = makeName!!)
    }
}

@Composable
@Preview
fun circularPreview() {
    ChekiCarsTheme {
        circularIcon(
            imageUrl = "https://storage.googleapis.com/img.autochek.africa/marketplace/audi.png",
            makeName = "Audi"
        )
    }
}


