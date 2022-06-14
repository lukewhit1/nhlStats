package com.example.nhlstatsapp.api

import android.util.Log
import com.example.nhlstatsapp.view.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PlayerStatsRepository {
    suspend fun getPlayersByName(playerName: String): Flow<UIState>
    suspend fun getSinglePlayerStats(playerId: Int): Flow<UIState>
}

class PlayerStatsRepositoryImpl @Inject constructor(private val searchService: SearchService,
                                                    private val singlePlayerService: SinglePlayerService): PlayerStatsRepository {
    override suspend fun getPlayersByName(playerName: String): Flow<UIState> = flow {
        try {
            val response = searchService.getPlayersByName(playerName = playerName)
            //val response = searchService.getPlayersByName()
            if (response.isSuccessful) {
                emit(response.body()?.let {
                    UIState.Success(it)
                } ?: throw Exception("Null Response"))
            } else {
                throw Exception("Failed Network Call")
            }
        } catch (e: Exception) {
            emit(UIState.Error(e))
        }
    }

    override suspend fun getSinglePlayerStats(playerId: Int): Flow<UIState> = flow {
        try {
            val response = singlePlayerService.getSinglePlayerStats(playerId = playerId)
            if (response.isSuccessful) {
                emit(response.body()?.let {
                    UIState.Success(it)
                } ?: throw Exception("NullResponse"))
            } else throw Exception("Failed network call")
        } catch (e: Exception) {
            emit(UIState.Error(e))
        }
    }


}