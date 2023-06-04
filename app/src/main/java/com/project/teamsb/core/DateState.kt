package com.project.teamsb.core

import com.project.teamsb.utils.christValue
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek

data class DateState(
    val weekState: WeekState,
    val dayOfWeek: DayOfWeek
){
    val date = weekState.start.plus(dayOfWeek.christValue, DateTimeUnit.DAY)

    val isSameMonth = weekState.monthState.month == date.month
}