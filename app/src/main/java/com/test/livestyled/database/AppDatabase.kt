package com.test.livestyled.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.test.livestyled.converters.DatesConverter
import com.test.livestyled.converters.ImagesConverter
import com.test.livestyled.converters.VenuesConverter
import com.test.livestyled.models.Event

//Create a connexion with ROOM
@Database(entities = [(Event::class)], version = 1, exportSchema = false)
@TypeConverters(DatesConverter::class, ImagesConverter::class, VenuesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var sInstance: AppDatabase? = null
        private val DATABASE_NAME: String = "EventsList"

        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                synchronized(AppDatabase::class) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase::class.java, DATABASE_NAME)
                            .build()
                }
            }
            return sInstance as AppDatabase
        }
    }

    abstract fun eventsDao(): EventsDao

}