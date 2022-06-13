package com.kangethe.chekicars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kangethe.chekicars.navigation.NavRoutes
import com.kangethe.chekicars.screens.*
import com.kangethe.chekicars.ui.theme.ChekiCarsTheme
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
                    MainScreen(myAutoCheckAPI)
                }
            }
        }
    }
}

@Composable
fun MainScreen(myAutoCheckAPI: MyAutoCheckAPI) {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
//            TopAppBar(
//                title = { Text("Explore") },
//                backgroundColor = MaterialTheme.colors.background,
//                navigationIcon = {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_baseline_grid_view_24),
//                        contentDescription = null,
//                        modifier = Modifier.padding(16.dp)
//                    )
//                },
//                elevation = 0.dp,
//                actions = {
//                    Icon(
//                        imageVector = Icons.Filled.ShoppingCart,
//                        contentDescription = null,
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//            )
        },
        content = { NavigationHost(navController = navController, myAutoCheckAPI) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

@Composable
fun NavigationHost(navController: NavHostController, myAutoCheckAPI: MyAutoCheckAPI) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            Home(myAutoCheckAPI, navController)
        }

        composable(
            NavRoutes.HomeDetail.route,
            arguments = listOf(navArgument("carId"){ type = NavType.StringType})
        ){ backStackEntry ->
            CarDetail( myAutoCheckAPI, backStackEntry.arguments?.getString("carId"),navController)
        }

        composable(NavRoutes.Likes.route) {
            Likes()
        }

        composable(NavRoutes.Notifications.route) {
            NotificationScreen()
        }

        composable(NavRoutes.Messages.route) {
            MessagesScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                }
            )
        }
    }
}

