package com.project.teamsb.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.project.teamsb.data.CalendarDateTime
import kotlinx.datetime.Month
import java.time.LocalDateTime
import kotlin.math.absoluteValue

fun showMessage(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun showDatePicker(context: Context, time: CalendarDateTime) {
    DatePickerDialog(
        context,
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            time.year = year
            time.month = Month(month)
            time.dayOfMonth = dayOfMonth
        },
        time.year,
        time.month.value,
        time.dayOfMonth
    ).show()
}
fun showTimePicker(context: Context, time: CalendarDateTime){
    TimePickerDialog(
        context,
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            time.hour = hourOfDay
            time.minute = minute
        },
        time.hour,
        time.minute,
        false
    ).show()
}

