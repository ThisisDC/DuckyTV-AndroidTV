package com.example.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.navigation.NavType
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import androidx.navigation.navArgument
    import com.example.duckytv.navigation.AppScreens
    import com.example.duckytv.screens.main.MainScreen
    import com.example.duckytv.screens.main.MainViewModel
    import com.example.duckytv.screens.player.PlayerScreen




@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = AppScreens.MainScreen.name){

        composable(AppScreens.MainScreen.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController,
                mainViewModel = mainViewModel)
        }

        val route = AppScreens.PlayerScreen.name
        composable("$route/{channelId}",
            arguments = listOf(
                navArgument(name = "channelId"){
                    type = NavType.StringType
                })){ navBack ->
            navBack.arguments?.getString("channelId").let { channelId ->
                PlayerScreen(navController = navController, channelId = channelId)
            }
        }

    }
}