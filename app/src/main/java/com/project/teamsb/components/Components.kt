package com.project.teamsb.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.teamsb.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarAppBar(modifier: Modifier = Modifier, title: String, onClicked: () -> Unit) {
    TopAppBar(
        modifier = modifier.height(100.dp),
        title = {
            Surface(modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Text(text = title, style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold))
                }
            }
        },
        actions = {
            Surface(modifier = Modifier.fillMaxHeight()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onClicked.invoke() }) {
                        Icon(modifier = Modifier.fillMaxSize(),imageVector = Icons.Default.AccountBox, contentDescription = null)
                    }
                }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {



    HorizontalPager(pageCount = Int.MAX_VALUE, state = PagerState(10)) {
        Text(text = "page $it")
    }
}

@Composable
fun FABContent(onTab: () -> Unit) {
    FloatingActionButton(
        onClick = { onTab.invoke() },
        shape = RoundedCornerShape(50.dp),
        containerColor = Color.Black
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun ScheduleColumn(item: Int){

}