package com.project.teamsb.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun showMessage(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}