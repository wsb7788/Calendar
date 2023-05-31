package com.project.teamsb.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.project.teamsb.model.Schedule
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val query: Query
) {
    suspend fun getSchedule(userId: String): List<Schedule> {

        try {
            query.whereEqualTo("user_id", userId).get().await().documents.map {
                return listOf(it.toObject(Schedule::class.java)!!)
            }
        }catch (e: FirebaseFirestoreException){
            Log.d("TAG", "getSchedule: ${e.message}")
        }
        return emptyList()
    }

}