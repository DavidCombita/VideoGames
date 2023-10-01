package com.softyouappsc.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softyouappsc.data.models.MinimumSystemRequirements
import com.softyouappsc.data.models.Screenshot

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromMinimumSystemRequirements(minimumSystemRequirements: MinimumSystemRequirements?): String? {
        return Gson().toJson(minimumSystemRequirements)
    }

    @TypeConverter
    @JvmStatic
    fun toMinimumSystemRequirements(json: String?): MinimumSystemRequirements? {
        return Gson().fromJson(json, MinimumSystemRequirements::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun fromScreenshotList(screenshots: List<Screenshot>?): String? {
        return Gson().toJson(screenshots)
    }

    @TypeConverter
    @JvmStatic
    fun toScreenshotList(json: String?): List<Screenshot>? {
        val type = object : TypeToken<List<Screenshot>>() {}.type
        return Gson().fromJson(json, type)
    }
}