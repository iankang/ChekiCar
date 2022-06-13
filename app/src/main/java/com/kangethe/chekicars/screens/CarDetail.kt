package com.kangethe.chekicars.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.Close
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kangethe.chekicars.R
import com.kangethe.chekicars.utils.commaStringExtensions.commaString
import com.kangethe.myautocheckapi.models.CarDetailResponse
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import com.kangethe.myautocheckapi.repository.MyAutoCheckAPI

@Composable
fun CarDetail(
    myAutoCheckAPI: MyAutoCheckAPI,
    carId: String?,
    navController: NavHostController? = null
) {
    val carDetails by getCarDetails(
        myAutoCheckAPI = myAutoCheckAPI,
        carId = carId,
        pageSize = 50,
        page = 1
    )
    if (carDetails.isOk) {
        Log.e("carDetails", carDetails.data.toString())
        val carDets = carDetails.data
            CarDetails(
                carDets?.imageUrl,
                carDets?.carName,
                carDets?.mileage,
                carDets?.mileageUnit,
                carDets?.city,
                carDets?.sellingCondition,
                carDets?.gradeScore?.toInt().toString(),
                carDets?.id,
                carDets?.year.toString(),
                carDets?.transmission,
                carDets?.engineType,
                carDets?.fuelType,
                carDets?.vin,
                carDets?.state,
                carDets?.country,
                carDets?.ownerType,
                carDets?.exteriorColor,
                carDets?.interiorColor,
                carDets?.hasWarranty,
                carDets?.hasFinancing,
                carDets?.insured,
                carDets?.sold,
                carDets?.marketplacePrice,
                carDets?.marketplaceOldPrice,
                carDets?.loanValue,
                carDets?.installment,
                navController
            )
    }
}

@Composable
fun TopCarDetail(navController: NavHostController? = null) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = null,
            modifier = Modifier.clickable {
                navController?.navigate("home")
            }
        )
    }
}

@Composable
fun getCarDetails(
    myAutoCheckAPI: MyAutoCheckAPI?,
    carId: String?,
    pageSize: Int,
    page: Int
): State<MyAutoCheckResponse<CarDetailResponse>> {
    return produceState(initialValue = MyAutoCheckResponse<CarDetailResponse>()) {
        val response = myAutoCheckAPI?.getCarDetails(carId, pageSize, page)
        if (response != null) {
            value = response
        }
    }
}

@Composable
@Preview(name = "carDetails", showBackground = true)
fun CarDetails(
    imageUrl: String? = null,
    carName: String? = "Pontiac Sunbird - 2019",
    mileage: Int? = 643783,
    mileageUnits: String? = "km",
    location: String? = "Kisumu",
    carState: String? = "Local",
    gradeScore: String? = "4",
    vehicleId: String? = "fR469BF_20",
    year: String? = "2019",
    transmission: String? = "automatic",
    engineType: String? = "4-cylinder(I4)",
    fuelType: String? = "hybrid",
    vin: String? = "RMAN*************",
    state: String? = "Mombasa",
    country: String? = "KE",
    ownerType: String? = "Individual",
    interiorColor: String? = "Red",
    exteriorColor: String? = "White",
    warranty: Boolean? = false,
    financed: Boolean? = false,
    insured: Boolean? = false,
    sold: Boolean? = false,
    marketPriceCurrent: Int? = null,
    marketPriceOld: Int? = null,
    loanValue: Double? = null,
    installment: Number? = null,
    navController: NavHostController? = null,
               ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(bottom = 70.dp)

        ) {
            item {
                TopCarDetail(navController)
            }
            item {
                DetailCarImage(
                    imageUrl
                )
            }

            item {
                CarDetailCard(mileage, mileageUnits, location, carState, gradeScore)
            }

            item {
                CarDetailTextMetaData(carName,vehicleId, year, transmission, engineType, fuelType, vin, state, country, ownerType, interiorColor)
            }

            item {
                CarDetailFinanceData(warranty, financed, insured, sold, marketPriceCurrent, marketPriceOld, loanValue, installment)
            }

        }
    }


@Composable
fun DetailCarImage(imageUrl: String? = null) {
    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.car_state_darker),
            error = painterResource(id = R.drawable.ic_baseline_car_error_24),
            contentScale = ContentScale.FillWidth,
            contentDescription = "car Image"
        )
    }
}

@Composable
fun KeyValueText(
    key: String? = null,
    value: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 2.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$key : ",
            fontWeight = FontWeight.Light
        )
        Text(
            text = value ?: "Value",
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun KeyValueCheckIcon(
    key: String? = null,
    isTrue: Boolean? = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 2.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$key : ",
            fontWeight = FontWeight.Light
        )
        if (isTrue == true) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = Color.Green
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}

@Composable
@Preview
fun CarDetailCard(
    mileage: Int? = 643783,
    mileageUnits: String? = "km",
    location: String? = "Kisumu",
    carState: String? = "Local",
    gradeScore: String? = "4"
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MetadataText(R.drawable.odometer, "${mileage?.commaString} $mileageUnits")
                MetadataText(R.drawable.location, location ?: "Location")
                MetadataText(R.drawable.car_state_darker, carState ?: "Local")
                MetadataText(R.drawable.star, gradeScore ?: "4")
            }
        }
    }
}

@Composable
@Preview
fun CarDetailTextMetaData(
    carName:String? = "Pontiac Sunbird - 2019",
    vehicleId:String? = "fR469BF_20",
    year:String? = "2019",
    transmission:String? = "automatic",
    engineType:String? = "4-cylinder(I4)",
    fuelType:String? = "hybrid",
    vin:String? = "RMAN*************",
    state:String? = "Mombasa",
    country:String? = "KE",
    ownerType:String? = "Individual",
    interiorColor:String? = "Red",
    exteriorColor:String? = "White"
) {
    Column {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = carName!!,
                style = MaterialTheme.typography.h5
            )
        }
        KeyValueText("Vehicle ID", vehicleId)
        KeyValueText("Year", year)
        KeyValueText("Transmission", transmission)
        KeyValueText("Engine Type", engineType)
        KeyValueText("Fuel Type", fuelType)
        KeyValueText("vin", vin)
        KeyValueText("State", state)
        KeyValueText("Country", country)
        KeyValueText("Owner Type", ownerType)
        KeyValueText("Interior Color", interiorColor)
        KeyValueText("Exterior Color", exteriorColor)

    }
}

@Composable
@Preview
fun CarDetailFinanceData(
    warranty:Boolean? = false,
    financed:Boolean? = false,
    insured:Boolean? = false,
    sold:Boolean? = false,
    marketPriceCurrent:Int? = null,
    marketPriceOld:Int? = null,
    loanValue:Double? = null,
    installment:Number? = null,
) {
    Card {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier.size(24.dp, 24.dp),
                    painter = painterResource(id = R.drawable.finance_book),
                    contentDescription = null
                )

                Text(
                    text = "Financial Information",
                    style = MaterialTheme.typography.h4
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        KeyValueCheckIcon("Warranty", warranty)
                        KeyValueCheckIcon("Financing", financed)
                        KeyValueCheckIcon("Insured", insured)
                        KeyValueCheckIcon("Sold", sold)
                    }

                }

                item {
                    Column {
                        KeyValueText("Vehicle C.V",marketPriceCurrent?.commaString)
                        KeyValueText("Vehicle O.V", marketPriceOld?.commaString)
                        KeyValueText("Loan Value", loanValue?.commaString)
                        KeyValueText("Installment", installment?.commaString)
                    }
                }
            }
        }
    }
}