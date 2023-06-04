package com.project.teamsb.core

import com.project.teamsb.data.CalendarDateTime
import com.project.teamsb.utils.christValue
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Month

data class MonthState(
    val year: Int,
    val month: Month
) : ClosedRange<CalendarDateTime> {
    private val firstDateOfMonth = CalendarDateTime(year, month, 1, 1, 1)

    override val start: CalendarDateTime = firstDateOfMonth.minus(
        value = firstDateOfMonth.dayOfWeek.christValue,
        unit = DateTimeUnit.DAY,
    )

    override val endInclusive: CalendarDateTime = start.plus(
        value = 41,
        unit = DateTimeUnit.DAY,
    )

}
