package com.softyouappsc.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.softyouappsc.models.utils.Converters

@Entity(tableName = "VideoGames")
@TypeConverters(Converters::class)
data class VideoGameDetail(
    @PrimaryKey val id: Int,
    val description: String? = null,
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val minimum_system_requirements: MinimumSystemRequirements? = null,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val screenshots: List<Screenshot>? = null,
    val short_description: String,
    val status: String? = null,
    val thumbnail: String,
    val title: String
){
    fun isComplete(): Boolean{
        return !description.isNullOrBlank() || minimum_system_requirements != null ||
                screenshots != null || !status.isNullOrBlank()
    }

}