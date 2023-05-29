package com.project.teamsb.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDateTime

fun showMessage(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun showDatePicker(context: Context, startTime: MutableState<LocalDateTime>){
    DatePickerDialog(
        context,
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            startTime.value = startTime.value.withYear(year)
            startTime.value = startTime.value.withMonth(month)
            startTime.value = startTime.value.withDayOfMonth(dayOfMonth)
        },
        startTime.value.year,
        startTime.value.monthValue,
        startTime.value.dayOfMonth
    ).show()
}
fun showTimePicker(context: Context, startTime: MutableState<LocalDateTime>){
    TimePickerDialog(
        context,
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            startTime.value = startTime.value.withHour(hourOfDay)
            startTime.value = startTime.value.withMinute(minute)
        },
        startTime.value.hour,
        startTime.value.minute,
        false
    ).show()
}