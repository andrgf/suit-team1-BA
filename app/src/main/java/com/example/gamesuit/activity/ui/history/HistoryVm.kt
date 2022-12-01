package com.example.gamesuit.activity.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.activity.data.retrofit.HistoryResponse
import com.example.gamesuit.activity.data.retrofit.Retrofit
import kotlinx.coroutines.launch
import java.lang.Exception

class HistoryVm : ViewModel() {

    private val sharedPreference = AppSharedPreference

    private val _loadInd = MutableLiveData<Boolean>()
    val loadInd: LiveData<Boolean> get() = _loadInd

    private val _historyList = MutableLiveData<List<HistoryResponse.History>>()
    val historyList: LiveData<List<HistoryResponse.History>> get() = _historyList

    private val _onError = MutableLiveData<String>()

    fun getHistory() {
        viewModelScope.launch {
            _loadInd.value = true
            try {
                val historyResponse =
                    Retrofit.retrofitService.getHistory("Bearer ${sharedPreference.userToken}")
                _historyList.value = historyResponse.data
                _loadInd.value = false
                Log.i("recycler_success", "${_historyList.value}")
            } catch (e: Exception) {
                _loadInd.value = false
                e.printStackTrace()
                _onError.value = "shit happens! ${e.message}"
                Log.i("recycler_fail", "${e.message}")
            }
        }
    }
}