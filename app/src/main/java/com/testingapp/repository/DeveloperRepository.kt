package com.testingapp.repository

import androidx.lifecycle.MutableLiveData

import com.testingapp.model.DeveloperModel
import com.testingapp.network.RetrofitClient
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.testingapp.model.ListModelItem

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DeveloperRepository {
    private val mmDeveloperModelmist = ArrayList<ListModelItem>()
    private val mutableLiveData = MutableLiveData<List<ListModelItem>>()

    ////call to internet and  retun  MutableLiveData
    fun getMutableLiveData(): MutableLiveData<List<ListModelItem>> {

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service

        ///call the API that define In Interface
        val call = userDataService.apiRequestsArray

        call.enqueue(object : Callback<JsonArray> {
            internal var message: String? = null

            override fun onResponse(call: Call<JsonArray>, resp: Response<JsonArray>) {

                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                val gson = GsonBuilder().create()

                if (resp != null && resp.body() != null) {

                    val json = JsonParser().parse(resp.body()!!.toString()).asJsonArray
                    //Log.e("data",json.toString())
                    if (json != null) {

                        for (i in 0..json.size() - 1) {
                            try {

                                val jsonobj =
                                    JsonParser().parse(json.get(i).toString()).asJsonObject

                                val responseModel =
                                    gson.fromJson(jsonobj, ListModelItem::class.java)

                                mmDeveloperModelmist.add(responseModel)

                            } catch (ex: Exception) {

                            }


                        }
                        mutableLiveData.value = mmDeveloperModelmist

                    }
                }


            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                t.printStackTrace()

            }
        })


        return mutableLiveData
    }

    companion object {

        private val TAG = "DeveloperRepository"
    }
}
