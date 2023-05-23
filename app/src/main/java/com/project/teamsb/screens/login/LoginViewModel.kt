package com.project.teamsb.screens.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassWord(email: String, password: String, home : () -> Unit) {

        Log.d(TAG, "signInWithEmailAndPassWord: $email $password")

        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            home.invoke()
                        }else{
                            try{
                                throw task.exception!!
                            } catch (e: FirebaseAuthException){
                                Log.d(TAG, "signInWithEmailAndPassWord: ${e.message}")
                            }
                        }
                    }
            } catch (e: Exception){
                Log.d("TAG", "signInWithEmailAndPassWord: ${e.message}")
            }
        }

    }

    fun createUserWithEmailAndPassword(email: String, password: String, home : () -> Unit) {

        viewModelScope.launch {

            try{
                if(_loading.value == false){
                    _loading.value = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful){

                                val displayName = task.result?.user?.email?.split("@")?.get(0)
                                createUser(displayName)
                                home.invoke()

                            }else{
                                
                                Log.d("TAG", "createUserWithEmailAndPassword: ${task.result}")
                            }
                            _loading.value = false
                        }
                }
            }catch (e: Exception){
                Log.d("TAG", "createUserWithEmailAndPassword: ${e.message}")
            }

        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid

    }
}
