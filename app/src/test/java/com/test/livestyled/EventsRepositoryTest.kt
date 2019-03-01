package com.test.livestyled

import com.test.livestyled.data.EventsRepository
import com.test.livestyled.data.Webservice
import com.test.livestyled.database.EventsDao
import com.test.livestyled.models.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.Executor
import org.mockito.Mockito.verify

class EventsRepositoryTest {

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    private lateinit var eventsDao: EventsDao
    private lateinit var api: Webservice

    private lateinit var eventsRepository: EventsRepository

    @Before
    fun setup() {
        eventsDao = Mockito.mock(EventsDao::class.java)
        api = Mockito.mock(Webservice::class.java)

        val executor = Executor { command -> command.run() }

        eventsRepository = EventsRepository(api, eventsDao, executor)

    }

    @Test
    fun `when adding to bag bagDao-addToBag is called`() {
        eventsRepository.getEvents()
        verify(eventsDao).insertEvents(listOf(Event(any(), any(), any(), any(), any(), any())))
        //verify(eventsDao).loadEvents()
    }

    @Test
    fun `eventsDao-getFavoriteEvents is called`() {
        eventsRepository.getFavoriteEvents(true)
        verify(eventsDao).getFavoriteEvents(true)
    }

    @Test
    fun `eventsDao-updateEvent is called`() {
        val event= Event("name", "id", listOf(Image("url")), Dates(Start("date")), Venues(listOf(Venue(Address("")))))
        eventsRepository.updateEvent(event)
        verify(eventsDao).updateEvent(event)
    }

}