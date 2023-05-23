package com.project.teamsb.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.project.teamsb.navigation.CalendarScreens

@Composable
fun LoginScreen(navController: NavController){

    Text(text = "Hello", Modifier.clickable { navController.navigate(CalendarScreens.HomeScreen.name) })
}