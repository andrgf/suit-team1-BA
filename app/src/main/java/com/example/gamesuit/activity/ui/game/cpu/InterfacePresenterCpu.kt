package com.example.gamesuit.activity.ui.game.cpu

interface InterfacePresenterCpu {
    fun comTurn(pilihanSatu: String)
    fun checkSuit(pilihanSatu: String, pilihanCom: Int)
}