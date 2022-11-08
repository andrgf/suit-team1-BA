package com.example.gamesuit.activity.ui.game.cpu

import kotlinx.coroutines.*

@DelicateCoroutinesApi
class PresenterCpu(
    private val playView: CpuView,
    private val namaPlayer: String,
) : InterfacePresenterCpu {

    override fun comTurn(
        pilihanSatu: String,
    ) {
        val comChoice = listOf(
            0, 1, 2
        ).random()

        GlobalScope.launch(Dispatchers.Default) {
            playView.comPlay(comChoice)
            runBlocking {
                checkSuit(pilihanSatu, comChoice)
            }
        }
    }

    override fun checkSuit(pilihanSatu: String, pilihanCom: Int) {
        var res = -2
        when (pilihanSatu) {
            "Batu" -> {
                when (pilihanCom) {
                    0 -> {
                        playView.hasil("SERI!", 0)
                        res = 0
                    }
                    1 -> {
                        playView.hasil("COM\nMENANG!", -1)
                        res = -1
                    }
                    2 -> {
                        playView.hasil("$namaPlayer\nMenang", 1)
                        res = 1
                    }
                }
            }
            "Kertas" -> {
                when (pilihanCom) {
                    0 -> {
                        playView.hasil("$namaPlayer\nMenang", 1)
                        res = 1
                    }
                    1 -> {
                        playView.hasil("SERI!", 0)
                        res = 0
                    }
                    2 -> {
                        playView.hasil("COM\nMENANG!", -1)
                        res = -1
                    }
                }
            }
            "Gunting" -> {
                when (pilihanCom) {
                    0 -> {
                        playView.hasil("COM\nMENANG!", -1)
                        res = -1
                    }
                    1 -> {
                        playView.hasil("$namaPlayer\nMenang", 1)
                        res = 1
                    }
                    2 -> {
                        playView.hasil("SERI!", 0)
                        res = 0
                    }
                }
            }
        }
        playView.createDialog(namaPlayer, res)
    }


}