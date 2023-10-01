package com.softyouappsc.models.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromMinimumSystemRequirements(minimumSystemRequirements: com.softyouappsc.models.MinimumSystemRequirements?): String? {
        return Gson().toJson(minimumSystemRequirements)
    }

    @TypeConverter
    @JvmStatic
    fun toMinimumSystemRequirements(json: String?): com.softyouappsc.models.MinimumSystemRequirements? {
        return Gson().fromJson(json, com.softyouappsc.models.MinimumSystemRequirements::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromScreenshotList(screenshots: List<com.softyouappsc.models.Screenshot>?): String? {
        return Gson().toJson(screenshots)
    }

    @TypeConverter
    @JvmStatic
    fun toScreenshotList(json: String?): List<com.softyouappsc.models.Screenshot>? {
        val type = object : TypeToken<List<com.softyouappsc.models.Screenshot>>() {}.type
        return Gson().fromJson(json, type)
    }
}