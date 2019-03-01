package com.test.livestyled.converters

import android.arch.persistence.room.TypeConverter
import com.test.livestyled.models.Dates
import com.test.livestyled.models.Start

/*
    Convert String to Date and Date to String
 */
class DatesConverter {
    @TypeConverter
    fun fromDates(value: Dates): String {
        return value.start.dateTime
    }

    @TypeConverter
    fun toDates(value: String): Dates {
        return Dates(Start(value))
    }
}
