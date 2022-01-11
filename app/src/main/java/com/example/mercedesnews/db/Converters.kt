package com.example.mercedesnews.db

import androidx.room.TypeConverter
import com.example.mercedesnews.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}