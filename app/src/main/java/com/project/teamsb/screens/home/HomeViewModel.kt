package com.project.teamsb.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.project.teamsb.model.Schedule
import com.project.teamsb.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FireRepository): ViewModel(){

    val data : MutableState<List<Schedule>> =
        mutableStateOf(listOf())

    private val currentUser = FirebaseAuth.getInstance().currentUser


    init{
        getSchedulesFromFireStore()
    }

    private fun getSchedulesFromFireStore() {
        viewModelScope.launch {
            data.value = repository.getSchedule(currentUser?.uid.toString())
            Log.d("TAG", "getSchedulesFromFireStore: ${data.value.toList()}")
        }
    }

}