package com.project.teamsb.data

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.reflect.KProperty


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

    public fun setDateTime(dateTime: CalendarDateTime){
        year = dateTime.year
        month = dateTime.month
        dayOfMonth = dateTime.dayOfMonth
        hour = dateTime.hour
        minute = dateTime.minute
    }

    public fun toLocalDateTime(): LocalDateTime = LocalDateTime(
        year = year,
        month = month,
        dayOfMonth = dayOfMonth,
        hour = hour,
        minute = minute
    )

    public fun toLocalDate(): LocalDate = LocalDate(
        year = year,
        month = month,
        dayOfMonth = dayOfMonth,
    )

    public fun toDateTime(): LocalTime = LocalTime(
        hour = hour,
        minute = minute
    )

    public fun plus(value: Int, unit: DateTimeUnit.DateBased): CalendarDateTime = CalendarDateTime(
        LocalDateTime(
            toLocalDate().plus(
                value = value,
                unit = unit
            ),
            toDateTime()
        )
    )

    public fun minus(value: Int, unit: DateTimeUnit.DateBased): CalendarDateTime = CalendarDateTime(
        LocalDateTime(
            toLocalDate().minus(
                value = value,
                unit = unit
            ),
            toDateTime()
        )
    )


    override fun compareTo(other: CalendarDateTime): Int = when {
        year != other.year -> compareValues(year, other.year)
        month != other.month -> compareValues(month, other.month)
        dayOfMonth != other.dayOfMonth -> compareValues(dayOfMonth, other.dayOfMonth)
        else -> compareValues(dayOfMonth, other.dayOfMonth)
    }



}