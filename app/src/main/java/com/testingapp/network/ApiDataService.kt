package com.testingapp.network


import com.google.gson.JsonArray

import retrofit2.Call
import retrofit2.http.GET

interface ApiDataService {


    @get:GET("posts")
    val apiRequestsArray: Call<JsonArray>

}
