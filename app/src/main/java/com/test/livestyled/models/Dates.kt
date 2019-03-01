package com.test.livestyled.models

import android.arch.persistence.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dates(@Embedded var start: Start)

class Start(@SerializedName("dateTime") @Expose var dateTime: String)