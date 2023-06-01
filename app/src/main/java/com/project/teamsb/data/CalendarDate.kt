package com.project.teamsb.data

import com.google.gson.JsonObject
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


public data class CalendarDateTime(
    var year: Int,
    var month: Month,
    var dayOfMonth: Int,
    var hour: Int,
    var minute: Int
) : Comparable<CalendarDateTime> {

    val dayOfWeek: DayOfWeek = toLocalDateTime().dayOfWeek

    public constructor(date: LocalDateTime) : this(
        year = date.year,
        month = date.month,
        dayOfMonth = date.dayOfMonth,
        hour = date.hour,
        minute = date.minute
    )

    public constructor() : this(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))


    public fun toMonthAndDayString() = "${month.value}월 ${dayOfMonth}일"
    public fun toHourAndMinuteString() = "${hour}:${minute}"


    public fun toLocalDateTime(): LocalDateTime = LocalDateTime(
        year = year,
        month = month,
        dayOfMonth = dayOfMonth,
        hour = hour,
        minute = minute
    )



    override fun compareTo(other: CalendarDateTime): Int = when {
        year != other.year -> compareValues(year, other.year)
        month != other.month -> compareValues(month, other.month)
        hour != other.hour -> compareValues(hour, other.hour)
        minute != other.minute -> compareValues(minute, other.minute)
        else -> compareValues(dayOfMonth, other.dayOfMonth)
    }
}