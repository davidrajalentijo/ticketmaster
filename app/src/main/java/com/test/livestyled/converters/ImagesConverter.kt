package com.test.livestyled.converters

import android.arch.persistence.room.TypeConverter
import com.test.livestyled.models.Image

/*
    Convert List of Images To String and String to List of Images
 */
class ImagesConverter {

    @TypeConverter
    fun fromImages(value: List<Image>): String {
        return value.first().url
    }

    @TypeConverter
    fun toImage(value: String): List<Image> {
        return listOf(Image(value))
    }
}

