package com.github.corbamico.ssrunning.data

import androidx.lifecycle.LiveData

enum class Status{
    Unknown,
    Checking,
    Idle,
    Running;

    override fun toString():String {
        return when (this) {
            Unknown -> "服务状态:   无连接"
            Checking-> "服务状态:   连接中"
            Running->  "服务状态:   已启动"
            Idle->     "服务状态:   已关闭"
        }
    }
}

data class ServerStatus (var status: Status)