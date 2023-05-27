package com.project.teamsb.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class Schedule(
    val name: String,
    val isImportant: Boolean,
    val isAllDay: Boolean,
    val alert: Boolean,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String,
    val color: Color,
)
