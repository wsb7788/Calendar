package com.project.teamsb.core

import com.project.teamsb.data.CalendarDateTime
import kotlinx.datetime.DateTimeUnit
import java.time.Month

data class WeekState(
    val monthState: MonthState,
    val weekOfMonth: Int
): ClosedRange<CalendarDateTime> {
    override val start = monthState.start.plus(weekOfMonth, DateTimeUnit.WEEK)
    override val endInclusive = start.plus(6, DateTimeUnit.DAY)
}
