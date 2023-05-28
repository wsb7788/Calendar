package com.project.teamsb.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.teamsb.components.CalendarAppBar
import com.project.teamsb.components.FABContent
import com.project.teamsb.components.HorizontalCalendar
import com.project.teamsb.navigation.CalendarScreens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {


    val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM"))
    //현재 날짜
    //이벤트 목록


    Scaffold(
        modifier = Modifier,
        topBar = {
            CalendarAppBar(modifier = Modifier, title = date) {
                navController.navigate(CalendarScreens.AddScreen.name)
            }
        },
        floatingActionButton = {
            FABContent(navController)
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues = paddingValues)){
            HorizontalCalendar()
            MaterialTheme.typography.bodyLarge

            Divider()

           /* LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(items = ){ schedule ->
                    ScheduleColumn(item = )
                }
            }*/
        }



    }

}