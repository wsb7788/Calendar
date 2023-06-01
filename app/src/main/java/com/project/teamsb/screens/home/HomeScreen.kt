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


    val state = rememberCalendarState()
    val title = remember(state.year, state.month){
        val format = SimpleDateFormat("yyyy.MM", Locale.getDefault())
        val instant = LocalDate(state.year, state.month, 1).atStartOfDayIn(TimeZone.currentSystemDefault())
        format.format(Date.from(instant.toJavaInstant()))
    }

    var listOfSchedules= emptyList<Schedule>()
    if(viewModel.data.value.isNotEmpty()){
        listOfSchedules = viewModel.data.value
        Log.d("TAG", "HomeScreen: $listOfSchedules")
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
            HorizontalCalendar(state, listOfSchedules)
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