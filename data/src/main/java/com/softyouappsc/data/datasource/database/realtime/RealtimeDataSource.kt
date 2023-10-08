package com.softyouappsc.data.datasource.database.realtime

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.softyouappsc.data.datasource.database.DataBaseDataSource
import com.softyouappsc.models.VideoGameDetail
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeDataSource @Inject constructor(
    private val database: DatabaseReference
): DataBaseDataSource {
    override fun getAllVideoGames(): Flow<List<VideoGameDetail>> = callbackFlow {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val videoGames = dataSnapshot.children.mapNotNull {
                    it.getValue(VideoGameDetail::class.java)
                }
                // Emit the updated list to the Flow
                trySend(videoGames)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                trySend(emptyList())
            }
        }
        // Add the ValueEventListener to the database reference
        database.addValueEventListener(postListener)

        // Use awaitClose to remove the listener when the Flow is canceled
        awaitClose {
            // Remove the ValueEventListener when the Flow is canceled
            database.removeEventListener(postListener)
        }
    }

    override fun saveVideoGameDetail(data: VideoGameDetail) {
        TODO("Not yet implemented save videogame")
    }

    override fun getVideoGameById(idVG: Int): Flow<VideoGameDetail> = callbackFlow {
        database.child("videogames").get().addOnSuccessListener {
            Log.e("traido", "se trajo la info ${(it.getValue() as VideoGameDetail).title}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    override fun deleteVideoGameDB(deleteGame: VideoGameDetail) {
        TODO("Not yet implemented delete game")
    }
}