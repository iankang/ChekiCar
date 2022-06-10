package com.kangethe.chekicars

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kangethe.chekicars.ui.theme.ChekiCarsTheme
import com.kangethe.myautocheckapi.models.MakesListResponse
import com.kangethe.myautocheckapi.models.MyAutoCheckResponse
import com.kangethe.myautocheckapi.repository.MyAutoCheckAPI
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val myAutoCheckAPI: MyAutoCheckAPI by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChekiCarsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    val apiState by getPopularMakes(myAutoCheckAPI = myAutoCheckAPI)
                    if(apiState.isOk){
                        Log.e("isOk",apiState.data.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChekiCarsTheme {
        Greeting("Android")
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