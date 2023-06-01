package com.project.teamsb.screens.add

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.project.teamsb.data.CalendarDateTime
import com.project.teamsb.model.FBSchedule
import kotlinx.coroutines.launch

class AddScreenViewModel : ViewModel(){

    val auth = Firebase.auth

    fun saveSchedule(
        title: String,
        isAllDay: Boolean,
        startTime: CalendarDateTime,
        endTime: CalendarDateTime,
        alert: Boolean,
        description: String,
        color: Color,
        home:() -> Unit
    ) {
        viewModelScope.launch {
            val userId = auth.currentUser?.uid
            try {

                val schedule = FBSchedule(
                    userId = userId.toString(),
                    title = title,
                    isImportant = false,
                    isAllDay = isAllDay,
                    start = startTime,
                    end = endTime,
                    alert = alert,
                    description = description,
                    color = color.toArgb()
                ).toMap()

                FirebaseFirestore.getInstance().collection("schedule")
                    .add(schedule)

                home.invoke()



            }catch (e:Exception){
                Log.d("TAG", "saveSchedule: ${e.message}")
            }
        }

    }

}