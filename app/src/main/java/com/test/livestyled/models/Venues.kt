package com.test.livestyled.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Venues(@SerializedName("venues") @Expose var venue: List<Venue>)

class Venue(@SerializedName("address") @Expose var address: Address)

class Address(@SerializedName("line1") @Expose var address1: String)