package com.test.livestyled.models

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "events")
class Event (
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: String,

    @ColumnInfo(name = "images")
    @SerializedName("images")
    @Expose
    var images: List<Image>,

    @ColumnInfo(name = "dates")
    @SerializedName("dates")
    @Expose
    var dates: Dates,

    @ColumnInfo(name = "venues")
    @SerializedName("_embedded")
    @Expose
    var venues: Venues,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

)
