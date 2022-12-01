package com.example.gamesuit.activity.ui.sound


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamesuit.activity.data.local.AppSharedPreference

class SoundVm : ViewModel() {

    fun setMusic(condition: Boolean) {
        AppSharedPreference.isMusicPlay = condition
    }
}


class SoundVmFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SoundVm::class.java)) {
            return SoundVm() as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}