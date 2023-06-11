package com.project.teamsb.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.teamsb.components.CalendarAppBar
import com.project.teamsb.components.FABContent
import com.project.teamsb.components.HorizontalCalendar
import com.project.teamsb.components.ScheduleColumn
import com.project.teamsb.core.rememberCalendarState
import com.project.teamsb.data.CalendarDateTime
import com.project.teamsb.model.Schedule
import com.project.teamsb.navigation.CalendarScreens
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toJavaInstant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.mutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {


    val calendarState = rememberCalendarState()
    val title = remember(calendarState.year, calendarState.month){
        val format = SimpleDateFormat("yyyy.MM", Locale.getDefault())
        val instant = LocalDate(calendarState.year, calendarState.month, 1).atStartOfDayIn(TimeZone.currentSystemDefault())
        format.format(Date.from(instant.toJavaInstant()))
    }

    var listOfSchedules= emptyList<Schedule>()
    if(viewModel.data.value.isNotEmpty()){
        listOfSchedules = viewModel.data.value
        Log.d("TAG", "HomeScreen: $listOfSchedules")
    }
    val clickState = remember {
        mutableStateOf(CalendarDateTime())
    }

    val selectedDayList = remember(clickState.value.year, clickState.value.month, clickState.value.dayOfMonth){
        println(clickState)
        listOfSchedules.filter { it.start?.month == clickState.value.month && it.start?.dayOfMonth == clickState.value.dayOfMonth }
    }


    Scaffold(
        modifier = Modifier,
        topBar = {
            CalendarAppBar(modifier = Modifier, title = title) {
                navController.navigate(CalendarScreens.AddScreen.name)
            }
        },
        floatingActionButton = {
            FABContent(navController)
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .background(color = androidx.compose.ui.graphics.Color.Red)
            .fillMaxHeight()){

            Surface(modifier = Modifier
                .fillMaxHeight()
                .weight(2f)) {

                HorizontalCalendar(calendarState, listOfSchedules, clickState)
            }


            Divider()



            if(listOfSchedules.isNotEmpty()){
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = androidx.compose.ui.graphics.Color.Yellow)
                    .weight(1f)){
                    items(items = selectedDayList){ schedule ->
                        ScheduleColumn(item = schedule)
                    }
                }
            }
        }



    }

}