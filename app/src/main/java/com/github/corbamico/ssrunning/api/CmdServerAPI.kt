package com.github.corbamico.ssrunning.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class CmdServerResponse{
    @SerializedName("result")
    var status: String? = null
}


interface CmdServerAPI {
    @GET("/status")
    abstract fun getStatus() :Call<CmdServerResponse>

    @GET("/rerun")
    abstract fun rerun() :Call<CmdServerResponse>

    @GET("/run")
    abstract fun run() :Call<CmdServerResponse>

    @GET("/kill")
    abstract fun stop() :Call<CmdServerResponse>


    companion object {

        fun create() : CmdServerAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CmdServerAPI::class.java)
        }
    }

}