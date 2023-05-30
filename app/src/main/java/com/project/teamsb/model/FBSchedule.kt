package com.project.teamsb.model

import androidx.compose.ui.graphics.Color
import com.google.firebase.Timestamp
import java.sql.Time
import java.time.LocalDateTime

data class FBSchedule(
    val userId: String,
    val title: String,
    val isImportant: Boolean,
    val isAllDay: Boolean,
    val alert: Boolean,
    val start: String,
    val end: String,
    val description: String,
    val color: Int,
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "title" to this.title,
            "is_important" to this.isImportant,
            "is_all_day" to this.isAllDay,
            "alert" to this.alert,
            "start" to this.start,
            "end" to this.end,
            "description" to this.description,
            "color" to this.color
        )

    }
}

