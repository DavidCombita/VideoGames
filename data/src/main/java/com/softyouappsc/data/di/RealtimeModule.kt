package com.softyouappsc.data.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RealtimeModule {

    @Provides
    fun getFirebaseInstance(): DatabaseReference =
        Firebase.database.reference.child("videogames")
}