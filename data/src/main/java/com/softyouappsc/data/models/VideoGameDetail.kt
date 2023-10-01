package com.softyouappsc.data.models

data class VideoGameDetail(
    val description: String? = null,
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val id: Int,
    val minimum_system_requirements: MinimumSystemRequirements? = null,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val screenshots: List<Screenshot>? = null ,
    val short_description: String,
    val status: String? = null,
    val thumbnail: String,
    val title: String
)