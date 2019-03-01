package com.test.livestyled

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.test.livestyled.data.EventsRepository
import com.test.livestyled.models.Event

/*
    ViewModel to manage the call between the view and the repository
 */
class MainViewModel(private val eventsRepository: EventsRepository) : ViewModel() {

    fun getEvents(): LiveData<List<Event>> {
        return eventsRepository.getEvents()
    }

    fun updateEvent(event: Event) {
        eventsRepository.updateEvent(event)
    }

    fun getFavoriteEvents(status: Boolean): LiveData<List<Event>> {
        return eventsRepository.getFavoriteEvents(status)
    }

}