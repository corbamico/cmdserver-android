package com.github.corbamico.ssrunning.data

import com.github.corbamico.ssrunning.api.CmdServerAPI
import com.github.corbamico.ssrunning.api.CmdServerResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerRepository {
    private val cmdServerAPI = CmdServerAPI.create()

    fun getStatus( onResult:(status:Status)->Unit ){
        val call = cmdServerAPI.getStatus()
        call.enqueue(object : Callback<CmdServerResponse>{
            override fun onResponse(call: Call<CmdServerResponse>, response: Response<CmdServerResponse>) {
                val status = response.body()?.status
                val res = if (status!=null) {
                    if (status=="1")
                        Status.Running
                    else Status.Idle
                } else {
                    Status.Unknown
                }
                onResult(res)
            }

            override fun onFailure(call: Call<CmdServerResponse>, t: Throwable) {
                onResult(Status.Unknown)
            }

        })
    }

    fun startServer(){

    }
    fun restartServer(){
        val call = cmdServerAPI.rerun()
        call.enqueue(object :Callback<CmdServerResponse>{
            override fun onResponse(call: Call<CmdServerResponse>, response: Response<CmdServerResponse>) {
            }

            override fun onFailure(call: Call<CmdServerResponse>, t: Throwable) {
            }
        })

    }
}