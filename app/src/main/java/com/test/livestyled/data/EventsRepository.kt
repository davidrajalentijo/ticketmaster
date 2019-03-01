package com.test.livestyled.data

import android.arch.lifecycle.LiveData
import com.test.livestyled.database.EventsDao
import com.test.livestyled.models.Event
import java.io.IOException
import java.util.concurrent.Executor

/**
 * Repository (repository pattern) to access events content. Events is maintained offline. EventsRepository is responsible from providing data to the upper layer
 * so it's not aware of where that data is coming from, local db or network.
 */
class EventsRepository(val webservice: Webservice, val eventsDao: EventsDao, val executor: Executor) {

    /*Get Events from Retrofit*/
    fun getEvents(): LiveData<List<Event>> {
        val data = eventsDao.loadEvents()
        executor.execute {
            try {
                val response = webservice.getEventsLondon().execute()
                if (response.isSuccessful && response.body() != null) {
                    //Check this, no the best approach
                    var check: Boolean = false
                    data.value?.forEachIndexed { index, event ->
                        check = event.id == response.body()!!.eventsInfo.events[index].id
                    }
                    if (!check) {
                        if (data.value!!.all { !it.favorite }) {
                            eventsDao.insertEvents(response.body()!!.eventsInfo.events)
                        }
                    }
                } else {
                    //TODO return error to the upper layer somehow
                }
            } catch (e: IOException) {
                //TODO return error to the upper layer somehow
            }
        }
        return data
    }

    /*Get favorite events */
    fun getFavoriteEvents(status: Boolean): LiveData<List<Event>> {
        return eventsDao.getFavoriteEvents(status)
    }

    /*Update an event*/
    fun updateEvent(event: Event) {
        executor.execute {
            try {
                eventsDao.updateEvent(event)
            } catch (e: IOException) {
                //TODO return error to the upper layer somehow
            }
        }
    }

}