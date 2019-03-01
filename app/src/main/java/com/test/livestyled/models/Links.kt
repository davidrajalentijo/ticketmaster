package com.test.livestyled.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links(@SerializedName("next") @Expose var next: URL)

class URL(@SerializedName("href") @Expose var href: String)