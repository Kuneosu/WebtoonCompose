package com.kuneosu.newcompose.data.data_source

import com.kuneosu.newcompose.data.model.ToonModel
import retrofit2.Call
import retrofit2.http.GET

interface ToonAPI {
    @GET("Toondata_api/main/data.json")
    fun getToonList(): Call<List<ToonModel>>
}
