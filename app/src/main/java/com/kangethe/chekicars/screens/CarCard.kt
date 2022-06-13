package com.kangethe.chekicars.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kangethe.chekicars.R
import com.kangethe.chekicars.ui.theme.ChekiCarsTheme
import com.kangethe.chekicars.ui.theme.Grey700
import com.kangethe.chekicars.ui.theme.Mustard500
import com.kangethe.chekicars.utils.commaStringExtensions.commaString

@Composable
fun CarCard(
    imageUrl: String? = null,
    yearOfManufacture: String? = null,
    carName: String? = null,
    carCostNow: String? = "4,341,232",
    carCostBefore: String? = "6,341,232",
    gradeScore: String? = "5.0",
    mileage: String? = "444,453",
    mileageUnits: String? = "km",
    location: String? = "Kisumu",
    carState: String? = "Local",
    carId: String,
    navController: NavController? = null
) {
    Card(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        elevation = 8.dp
    ) {
        Column {
            CarImageBox(imageUrl, yearOfManufacture, carName)
            CarTextCard(
                carCostNow,
                carCostBefore,
                gradeScore,
                mileage,
                mileageUnits,
                location,
                carState,
                carId,
                navController
            )
        }
    }
}

@Composable
fun CarImageBox(
    imageUrl: String? = null,
    yearOfManufacture: String? = null,
    carName: String? = null
) {
    Card(
        modifier = Modifier
            .height(200.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.car_state_darker),
                error = painterResource(id = R.drawable.ic_baseline_car_error_24),
                contentScale = ContentScale.FillWidth,
                contentDescription = "car Image"
            )
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "like Button",
                tint = Color.Red,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomStart)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = yearOfManufacture ?: "Year",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = carName ?: "Car Name",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.gallery),
                contentDescription = "gallery",
                tint = Color.Red,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        Log.e("clicking", "clicked")
                    })
        }
    }
}

@Composable
@Preview
fun ImageBoxPreview() {
    ChekiCarsTheme {
        CarImageBox()
    }
}

@Composable
fun CarTextCard(
    carCostNow: String? = "4,341,232",
    carCostBefore: String? = "6,341,232",
    gradeScore: String? = "5.0",
    mileage: String? = "444,453",
    mileageUnits: String? = "km",
    location: String? = "Kisumu",
    carState: String? = "Local",
    carId: String,
    navController: NavController? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = {
                    Log.e("clicked", "touched id: $carId")
                    navController?.navigate("home/$carId")
                }
            ),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row {
                    Text(
                        text = " Ksh. ${carCostNow?.commaString}",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " Ksh. ${carCostBefore?.commaString}",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Light,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                }


                Row {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "gradeScore",
                        tint = Mustard500
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "($gradeScore)",
                        color = Grey700,
                        textAlign = TextAlign.Center
                    )
                }
            }

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
            }
        }
    }
}

@Composable
fun MetadataText(
    iconResource: Int,
    meta: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(16.dp, 16.dp),
            painter = painterResource(id = iconResource),
            contentDescription = "icon description",
            tint = Color.Black
        )
        Text(
            text = meta,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Medium
        )

    }

}

@Composable
@Preview
fun CarTextCardPreview() {
    ChekiCarsTheme {
        CarTextCard(carId = "carId")
    }
}

@Composable
@Preview(name = "day", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "night", uiMode = UI_MODE_NIGHT_NO)
fun CarCardPreview() {
    ChekiCarsTheme {
        CarCard(carId = "carId")
    }
}