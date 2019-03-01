package com.test.livestyled.converters

import android.arch.persistence.room.TypeConverter
import com.test.livestyled.models.Address
import com.test.livestyled.models.Venue
import com.test.livestyled.models.Venues

/*
      Convert Venues to String and String to Venues
 */
class VenuesConverter {

    @TypeConverter
    fun fromVenues(value: Venues): String? {
        return value.venue.first().address.address1
    }

    @TypeConverter
    fun toVenues(value: String): Venues {
        return Venues(listOf(Venue(Address(value))))
    }
}