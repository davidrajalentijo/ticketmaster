package com.test.livestyled.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.test.livestyled.models.Event
import com.test.livestyled.models.Events

/*
    Operations to ROOM Database to insert, delete, update or get events
 */
@Dao
abstract class EventsDao {

    @Query("SELECT * FROM events")
    abstract fun loadEvents(): LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM events Where favorite=:status")
    abstract fun getFavoriteEvents(status: Boolean): LiveData<List<Event>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateEvent(event: Event)

}