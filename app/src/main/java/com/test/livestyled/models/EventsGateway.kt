package com.test.livestyled.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventsGateway(
        @SerializedName("_embedded")
        @Expose
        var eventsInfo: Events,

        @SerializedName("_links")
        @Expose
        var links: Links
)
