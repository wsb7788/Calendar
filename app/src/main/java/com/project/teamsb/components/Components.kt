package com.project.teamsb.components

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.teamsb.R
import com.project.teamsb.core.CalendarState
import com.project.teamsb.core.DateState
import com.project.teamsb.core.MonthState
import com.project.teamsb.core.WeekState
import com.project.teamsb.data.CalendarDateTime
import com.project.teamsb.model.Schedule
import com.project.teamsb.utils.showDatePicker
import com.project.teamsb.utils.showTimePicker
import com.project.teamsb.utils.toDayOfWeek
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import java.util.Locale


@Composable
fun CalendarLogo() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.app_name),
        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold)
    )
}


@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction

    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    val visualTransformation =
        if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value, onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(), enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction
    )

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {

        Icons.Default.Close
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar(
    modifier: Modifier = Modifier,
    title: String = "일정 추가",
    onBackClicked: () -> Unit,
    onSaveClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Surface(modifier = Modifier.fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { onBackClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "ArrowBack",
                        )
                    }
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                    Box {}
                }
            }
        },
        actions = {
            IconButton(onClick = { onSaveClicked.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Logout",
                )

            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarAppBar(modifier: Modifier = Modifier, title: String, onClicked: () -> Unit) {
    TopAppBar(
        modifier = modifier.height(100.dp),
        title = {
            Surface(modifier = Modifier.fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        },
        actions = {
            Surface(modifier = Modifier.fillMaxHeight()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onClicked.invoke() }) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar(
    calendarState: CalendarState,
    listOfSchedules: List<Schedule>,
    clickState: MutableState<CalendarDateTime>
) {


    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan),
        pageCount = Int.MAX_VALUE,
        state = calendarState.pagerState
    ) { page ->
        Column(modifier = Modifier.fillMaxHeight()) {
            val monthState = remember {
                MonthState(
                    year = page / 12 + 1,
                    month = Month.of(page % 12 + 1)
                )
            }
            val filteredMonthSchedule = remember(monthState) {
                listOfSchedules.filter { monthState.month == it.start?.month }
            }

            MonthHeader()

            Month(
                monthState = monthState,
                filteredMonthSchedule = filteredMonthSchedule,
                clickState
            )


        }
    }

}

@Composable
fun Month(
    monthState: MonthState,
    filteredMonthSchedule: List<Schedule>,
    clickState: MutableState<CalendarDateTime>
) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .background(Color.Gray)
    ) {
        val repeatWeek = remember(monthState) {
            if (monthState.firstDateOfMonth.dayOfWeek == DayOfWeek.SATURDAY) 6 else 5
        }
        repeat(repeatWeek) { week ->
            val weekState = remember {
                WeekState(
                    monthState = monthState,
                    weekOfMonth = week
                )
            }

            Week(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f / repeatWeek.toFloat())
                    .weight(1f),
                weekState = weekState,
                filteredMonthSchedule = filteredMonthSchedule,
                clickState = clickState
            )


        }

    }


}

@Composable
fun Week(
    modifier: Modifier,
    weekState: WeekState,
    filteredMonthSchedule: List<Schedule>,
    clickState: MutableState<CalendarDateTime>
) {
    Row(modifier = modifier) {
        repeat(7) {
            val dateState = remember {
                DateState(
                    weekState = weekState,
                    dayOfWeek = it.toDayOfWeek()
                )
            }


            Date(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                dayOfMonth = dateState.date.dayOfMonth,
                dayOfWeek = dateState.date.dayOfWeek,
                isSameMonth = dateState.isSameMonth,
                dateState = dateState,
                filteredMonthSchedule = filteredMonthSchedule,
                clickState = clickState,

                ) {
                clickState.value = dateState.date
            }
        }
    }


}

@Composable
fun Date(
    modifier: Modifier,
    dayOfMonth: Int,
    dayOfWeek: DayOfWeek,
    dateState: DateState,
    isSameMonth: Boolean,
    filteredMonthSchedule: List<Schedule>,
    clickState: MutableState<CalendarDateTime>,
    onClicked: () -> Unit
) {

    val isClickedDate = remember(clickState.value) {
        clickState.value == dateState.date
    }
    val now = CalendarDateTime()
    val isToday = dateState.date.year == now.year && dateState.date.month == now.month && dateState.date.dayOfMonth == now.dayOfMonth

    Column(modifier = modifier
        .background(color = Color.Blue)
        .clickable { onClicked() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = if (isClickedDate) {
                Modifier.sizeIn(
                    minWidth = with(LocalDensity.current) {
                        (MaterialTheme.typography.bodyMedium.fontSize * 1.5).toDp()
                    },
                    minHeight = with(LocalDensity.current) {
                        (MaterialTheme.typography.bodyMedium.fontSize * 1.5).toDp()
                    },
                ).background(color = Color.Black, shape = CircleShape)
            } else if (isToday) {
                Modifier.sizeIn(
                    minWidth = with(LocalDensity.current) {
                        (MaterialTheme.typography.bodyMedium.fontSize * 1.5).toDp()
                    },
                    minHeight = with(LocalDensity.current) {
                        (MaterialTheme.typography.bodyMedium.fontSize * 1.5).toDp()
                    },
                ).background(color = Color.LightGray, shape = CircleShape)
            } else {
                Modifier.sizeIn(
                    minWidth = with(LocalDensity.current) {
                        (MaterialTheme.typography.bodyMedium.fontSize * 1.5).toDp()
                    },
                    minHeight = with(LocalDensity.current) {
                        (MaterialTheme.typography.bodyMedium.fontSize * 1.5).toDp()
                    },
                )
            },contentAlignment = Alignment.Center){
                Text(
                    color = when {
                        isClickedDate || isToday -> Color.White
                        dayOfWeek == DayOfWeek.SATURDAY -> Color.Blue
                        dayOfWeek == DayOfWeek.SUNDAY -> Color.Red
                        else -> Color.Black
                    }.copy(alpha = if (isSameMonth) 1f else 0.5f),
                    style = MaterialTheme.typography.bodyMedium,
                    text = dayOfMonth.toString(),
                    textAlign = TextAlign.Center
                )
            }
        }
        Text(text = dayOfMonth.toString())
        Text(text = dayOfMonth.toString())
    }

}


@Composable
fun MonthHeader() {
    Row(Modifier.wrapContentHeight()) {
        repeat(7) {
            WeekDay(modifier = Modifier.weight(1F), dayOfWeek = it.toDayOfWeek())
        }
    }
}

@Composable
fun WeekDay(modifier: Modifier, dayOfWeek: DayOfWeek) {
    Text(
        modifier = modifier,
        text = remember {
            dayOfWeek.getDisplayName(
                java.time.format.TextStyle.NARROW_STANDALONE,
                Locale.getDefault()
            )
        },
        color = when (dayOfWeek) {
            DayOfWeek.SUNDAY -> Color.Red
            DayOfWeek.SATURDAY -> Color.Blue
            else -> Color.Black
        },
        textAlign = TextAlign.Center
    )
}

@Composable
fun FABContent(navController: NavController) {
    val isExpended = remember {
        mutableStateOf(false)
    }

    val transition = updateTransition(targetState = isExpended, label = "transition")

    val rotate by transition.animateFloat(label = "rotate") {
        if (it.value) 45f else 0f
    }


    FloatingActionButton(
        onClick = {
            isExpended.value = !isExpended.value
            Log.d("TAG", "FABContent: ${isExpended.value}")
        },
        modifier = Modifier.rotate(rotate),
        shape = RoundedCornerShape(50.dp),
        containerColor = Color.Black
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
    }
}


@Composable
fun ScheduleColumn(item: Schedule) {
    Column() {
        Text("asdasd")

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScheduleForm(
    title: MutableState<String>,
    isAllDay: MutableState<Boolean>,
    startTime: CalendarDateTime,
    endTime: CalendarDateTime,
    alert: MutableState<Boolean>,
    description: MutableState<String>,
    color: MutableState<Color>,
    navController: NavController
) {

    val colorRow = arrayOf(Color.Blue, Color.Red, Color.Yellow)
    Column() {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            onValueChange = { title.value = it },
            singleLine = true,
            placeholder = { Text(text = "일정을 입력하세요.") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Divider(
            modifier = Modifier.padding(vertical = 10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        Column(modifier = Modifier.padding(horizontal = 15.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "종일")
                Switch(
                    checked = isAllDay.value,
                    onCheckedChange = { isAllDay.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedBorderColor = Color.Black,
                        checkedTrackColor = Color.Black,
                        uncheckedThumbColor = Color.White,
                        uncheckedBorderColor = Color.LightGray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = startTime.toMonthAndDayString(),
                    modifier = Modifier.clickable {
                        showDatePicker(
                            navController.context,
                            startTime
                        )
                    })
                if (!isAllDay.value) {
                    Text(
                        text = startTime.toHourAndMinuteString(),
                        modifier = Modifier.clickable {
                            showTimePicker(
                                navController.context,
                                startTime
                            )
                        })
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = endTime.toMonthAndDayString(),
                    modifier = Modifier.clickable {
                        showDatePicker(
                            navController.context,
                            endTime
                        )
                    })
                if (!isAllDay.value) {
                    Text(
                        text = endTime.toHourAndMinuteString(),
                        modifier = Modifier.clickable {
                            showTimePicker(
                                navController.context,
                                endTime
                            )
                        })
                }
            }


            Spacer(modifier = Modifier.height(10.dp))



            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification Icon"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "알림")
                    Text(text = "없음")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification Icon"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "색")

                    LazyRow() {
                        items(colorRow) { item ->
                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .clickable { color.value = item }
                                    .background(item)
                                    .border(
                                        width = if (color.value == item) 2.dp else 0.dp,
                                        color = if (color.value == item) Color.Black else Color.Transparent,
                                        shape = CircleShape
                                    )
                            )

                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 150.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray
                ),
                value = description.value,
                onValueChange = { description.value = it },
                placeholder = { Text(text = "메모를 입력하세요.", color = Color.LightGray) },
            )
        }


    }
}
