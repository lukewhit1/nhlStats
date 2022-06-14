package com.example.nhlstatsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nhlstatsapp.api.PlayerStatsRepository
import com.example.nhlstatsapp.view.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val repository: PlayerStatsRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    // Using viewModelScope with our exception handler
    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    // For logging errors of the coroutine
    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("ViewModel", "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}", throwable)
        }
    }

    private val _searchList = MutableLiveData<UIState>()
    val searchList: LiveData<UIState> get() = _searchList

    fun getPlayersByName(playerName: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getPlayersByName(playerName).collect{
                _searchList.postValue(it)
            }
        }
    }


    private val _singleStats = MutableLiveData<UIState>()
    val singleStats: LiveData<UIState> get() = _singleStats

    fun getSinglePlayerStats(playerId: Int) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getSinglePlayerStats(playerId).collect {
                _singleStats.postValue(it)
            }
        }
    }

    fun setSearchLoadingState() { _searchList.value = UIState.Loading}
    fun setStatsLoadingState() { _singleStats.value = UIState.Loading}
}