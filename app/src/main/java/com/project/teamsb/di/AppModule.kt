package com.project.teamsb.di

import com.google.firebase.firestore.FirebaseFirestore
import com.project.teamsb.repository.FireRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireRepository() =
        FireRepository(
            query = FirebaseFirestore.getInstance()
                .collection("schedule")
        )
}