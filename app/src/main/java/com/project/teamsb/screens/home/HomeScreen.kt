package com.project.teamsb.screens.home

import android.text.format.DateFormat
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.teamsb.components.CalendarAppBar
import com.project.teamsb.components.FABContent
import com.project.teamsb.components.HorizontalCalendar
import com.project.teamsb.core.rememberCalendarState
import com.project.teamsb.model.Schedule
import com.project.teamsb.navigation.CalendarScreens
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toJavaLocalDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    val todayList = remember(calendarState){
        listOfSchedules.filter { it.start?.month == calendarState.month }
        //todo 날짜 선택했을 때 필터 추가
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

        Column(modifier = Modifier.padding(paddingValues = paddingValues)){

            HorizontalCalendar(calendarState, listOfSchedules)

            MaterialTheme.typography.bodyLarge

            Divider()

            /*LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(items = ){ schedule ->
                    ScheduleColumn(item = )
                }
            }*/
        }



    }

}