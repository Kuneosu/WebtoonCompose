package com.kuneosu.newcompose.data.data_source

import android.util.Log
import com.kuneosu.newcompose.data.model.ToonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG: String = "REMOTE DATA SOURCE"

class RemoteDataSource {

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/Kuneosu/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    private val retrofitAPI: ToonAPI = retrofit.create(ToonAPI::class.java)

    fun getToons(): List<String> {
        val results = mutableListOf<String>()
        val call: Call<List<ToonModel>> = retrofitAPI.getToonList()

        call.enqueue(object : Callback<List<ToonModel>> {
            override fun onResponse(
                call: Call<List<ToonModel>>,
                response: Response<List<ToonModel>>,
            ) {
                Log.d(TAG, "onResponse 성공")
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    for (toon in responseBody) {
                        results.add(toon.titleImage.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<ToonModel>>, t: Throwable) {
                Log.d(TAG, "onFailure 실패")
            }
        })
        return results
    }
}

