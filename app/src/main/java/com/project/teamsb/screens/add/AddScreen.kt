package com.project.teamsb.screens.add

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.teamsb.components.AddAppBar
import com.project.teamsb.components.AddScheduleForm
import com.project.teamsb.data.CalendarDateTime
import com.project.teamsb.utils.showMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController, viewModel: AddScreenViewModel = hiltViewModel()) {

    val title = remember { mutableStateOf("") }
    val isAllDay = remember { mutableStateOf(false) }
    val startTime = remember { CalendarDateTime() }
    val endTime = remember { CalendarDateTime() }
    val alert = remember { mutableStateOf(false) }
    val description = remember { mutableStateOf("") }
    val color = remember{mutableStateOf(Color.Blue)}
    val isValid = remember(title.value) { title.value.trim().isNotEmpty() }



    Scaffold(
        topBar = {
            AddAppBar(
                title = "일정 추가",
                onBackClicked = { navController.popBackStack() },
                onSaveClicked = {
                    if (isValid) {
                        Log.d("TAG", "AddScreen: title = ${title.value}, isallday = ${isAllDay.value}, desc = ${description.value} $isValid")
                        viewModel.saveSchedule(title.value, isAllDay.value, startTime, endTime, alert.value, description.value, color.value){
                            navController.popBackStack()
                        }
                        //저장하기!
                    } else {
                        showMessage(context = navController.context, text = "asd")
                        return@AddAppBar
                    }
                })
        },
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            AddScheduleForm(
                title = title,
                isAllDay = isAllDay,
                startTime = startTime,
                endTime = endTime,
                alert = alert,
                description = description,
                color = color,
                navController = navController
            )
        }

    }

}