package com.project.teamsb.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.project.teamsb.data.CalendarDateTime
import kotlinx.datetime.Month

@Stable
class CalendarState @OptIn(ExperimentalFoundationApi::class)
internal constructor(
    internal val pagerState: PagerState
) {
    @OptIn(ExperimentalFoundationApi::class)
    public constructor(initialDate: CalendarDateTime) : this (
        PagerState(
            initialPage = (initialDate.year-1) * 12 + initialDate.month.ordinal
        )
    )

    @OptIn(ExperimentalFoundationApi::class)
    public val year: Int by derivedStateOf { pagerState.currentPage / 12 + 1 }
    @OptIn(ExperimentalFoundationApi::class)
    public val month: Month by derivedStateOf { Month.of(pagerState.currentPage % 12 + 1) }


    @OptIn(ExperimentalFoundationApi::class)
    public suspend fun animateScrollToPrevious() {
        pagerState.animateScrollToPage(pagerState.currentPage - 1)
    }

    @OptIn(ExperimentalFoundationApi::class)
    public suspend fun animateScrollToNext() {
        pagerState.animateScrollToPage(pagerState.currentPage + 1)
    }


}