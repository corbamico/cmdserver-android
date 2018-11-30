package com.github.corbamico.ssrunning.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.corbamico.ssrunning.data.ServerRepository
import com.github.corbamico.ssrunning.data.ServerStatus
import com.github.corbamico.ssrunning.data.Status


class ServerViewModel(application: Application): AndroidViewModel(application){
    private val repository :ServerRepository = ServerRepository()
    val serverStatus:MutableLiveData<ServerStatus> = MutableLiveData<ServerStatus>().apply { Status.Unknown }

    fun retriveSever(){
        repository.getStatus { serverStatus.postValue(ServerStatus(it)) }
    }

    fun startServer() = repository.startServer()
    fun restartServer() = repository.restartServer()
}


