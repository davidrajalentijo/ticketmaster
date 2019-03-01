package com.test.livestyled

import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.test.livestyled.database.AppDatabase
import com.test.livestyled.database.EventsDao
import com.test.livestyled.models.*
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import java.io.IOException
import org.mockito.Mock
import android.arch.lifecycle.LiveData
import android.support.annotation.Nullable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EventsDaoTest {
    private lateinit var userDao: EventsDao
    private lateinit var db: AppDatabase

    @Mock
    private val observer: Observer<List<Event>>? = null

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        db = Room.inMemoryDatabaseBuilder(
                context, AppDatabase::class.java).build()
        userDao = db.eventsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val event: Event = Event("name", "id", listOf(Image("url")), Dates(Start("date")), Venues(listOf(Venue(Address("")))))
        userDao.insertEvents(listOf(event))
        val getEvents = userDao.loadEvents()
        this.observer?.let { getEvents.observeForever(it) }
        MatcherAssert.assertThat(LiveDataTestUtil.getValue(getEvents).first().id, CoreMatchers.equalTo(event.id))
    }

}

object LiveDataTestUtil {
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(@Nullable o: T?) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(4, TimeUnit.SECONDS)

        return data[0] as T
    }
}