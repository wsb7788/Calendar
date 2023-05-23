package com.project.teamsb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.teamsb.screens.home.HomeScreen
import com.project.teamsb.screens.login.LoginScreen
import com.project.teamsb.screens.SplashScreen

@Composable
fun CalendarNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = CalendarScreens.HomeScreen.name){

        composable(route = CalendarScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(route = CalendarScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(route = CalendarScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }

    }
}