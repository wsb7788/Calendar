package com.project.teamsb.screens.home

import android.text.format.DateUtils
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.type.Date
import com.project.teamsb.components.Calendar
import com.project.teamsb.components.CalendarAppBar
import com.project.teamsb.components.FABContent
import com.project.teamsb.components.ScheduleColumn
import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {


    val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM"))

    Scaffold(
        modifier = Modifier,
        topBar = {
            CalendarAppBar(modifier = Modifier, title = date) {
                Log.d("TAG", "HomeScreen: actionClicked")
            }
        },
        floatingActionButton = {
            FABContent(navController)
        }
    ) { paddingValues ->



        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize())
        {

            Calendar(viewModel = viewModel)

            LazyColumn(contentPadding = PaddingValues(10.dp)){
                items(emptyList<Int>()){ item ->
                    ScheduleColumn(item)
                }
            }

        }


    }

}