package com.test.livestyled.data

import com.test.livestyled.models.EventsGateway
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/*
    Different calls to TicketMaster API
 */
interface Webservice {

    @GET("events.json?city=London&size=50&apikey=5ike7MSNlAAvxYKqXhSyNY324bnkkwld")
    fun getEventsLondon(): Call<EventsGateway>

    @GET
    fun getNextEventsPage(@Url url: String): Call<EventsGateway>

}