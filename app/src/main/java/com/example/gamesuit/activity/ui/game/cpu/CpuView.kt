package com.example.gamesuit.activity.ui.game.cpu


interface CpuView {
    fun hasil(hasil: String, status: Int)
    suspend fun comPlay(id: Int)
    fun createDialog(resultString: String, result: Int)
}