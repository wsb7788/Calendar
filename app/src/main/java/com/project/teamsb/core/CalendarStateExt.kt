package com.project.teamsb.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import com.project.teamsb.data.CalendarDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
public fun rememberCalendarState(
    initialDate: CalendarDateTime = CalendarDateTime(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())),
): CalendarState = rememberSaveable(
    saver = mapSaver(
        save = {
            mapOf(
                "initialPage" to it.pagerState.currentPage,
            )
        },
        restore = {
            CalendarState(
                PagerState(
                    initialPage = it["initialPage"] as Int,
                ),
            )
        },
    ),
) {
    CalendarState(initialDate)
}
