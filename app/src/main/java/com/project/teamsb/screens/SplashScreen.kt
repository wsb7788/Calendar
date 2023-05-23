package com.project.teamsb.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.teamsb.R
import com.project.teamsb.navigation.CalendarScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val editable = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {

        delay(800)
        editable.value = false
        delay(800)

        navController.navigate(CalendarScreens.LoginScreen.name)

    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(contentAlignment = Alignment.Center) {
            AnimatedVisibility(
                visible = editable.value,
                enter = fadeIn(animationSpec = tween(400)),
                exit = fadeOut(animationSpec = tween(800))
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }


}