package com.example.newsapp.converter

import androidx.room.TypeConverter
import com.example.newsapp.home.data.model.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(value: String): Source {
        return Source(value, value)
    }

}