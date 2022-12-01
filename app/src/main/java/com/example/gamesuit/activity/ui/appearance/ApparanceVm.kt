package com.example.gamesuit.activity.ui.appearance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamesuit.activity.data.local.AppSharedPreference

class ApparanceVm :
    ViewModel() {
    private val _isChecked = MutableLiveData<Boolean>(AppSharedPreference.isDarkMode)
    val isChecked: LiveData<Boolean> = _isChecked

    fun setIsChecked() {
        _isChecked.value = !_isChecked.value!!
    }

    fun setTheme(condition: Boolean) {
        AppSharedPreference.isDarkMode = condition
    }

}


class SettingVmFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApparanceVm::class.java)) {
            return ApparanceVm() as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}