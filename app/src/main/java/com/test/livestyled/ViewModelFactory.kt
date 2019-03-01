package com.test.livestyled

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.google.gson.GsonBuilder
import com.test.livestyled.data.EventsRepository
import com.test.livestyled.data.Webservice
import com.test.livestyled.database.AppDatabase
import com.test.livestyled.database.EventsDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/*
    ModelFactory to create retrofitConnection
 */
class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(createEventsRepo(context)) as T
    }

    private fun createEventsRepo(context: Context): EventsRepository {
        return EventsRepository(createWebService(), createUserDao(context), createExecutor())
    }

    private fun createWebService(): Webservice {
        val gson = GsonBuilder().create();
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(Webservice::class.java)
    }

    private fun createExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    private fun createUserDao(context: Context): EventsDao {
        val d: AppDatabase = AppDatabase.getInstance(context)
        return d.eventsDao()
    }

    companion object {
        const val BASE_URL = "https://app.ticketmaster.com/discovery/v2/"

    }
}