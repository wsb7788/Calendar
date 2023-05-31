package com.project.teamsb.model

import androidx.compose.ui.graphics.Color
import com.google.firebase.firestore.PropertyName
import java.time.LocalDateTime

data class Schedule(
    @get: PropertyName("user_id")
    @set: PropertyName("user_id")
    var userId: String? = null,
    var title: String? = null,
    @get: PropertyName("is_important")
    @set: PropertyName("is_important")
    var isImportant: Boolean? = null,
    @get: PropertyName("is_all_day")
    @set: PropertyName("is_all_day")
    var isAllDay: Boolean? = null,
    var alert: Boolean? = null,
    var start: String? = null,
    var end: String? = null,
    var description: String? = null,
    var color: Int? = null,
)