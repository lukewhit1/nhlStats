package com.example.nhlstatsapp.api

import com.example.nhlstatsapp.model.SearchByNameResponse
import com.example.nhlstatsapp.model.StatElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @GET("{player_name}/$LIMIT")
    suspend fun getPlayersByName(
        @Path("player_name") playerName: String
    ): Response<SearchByNameResponse>

    companion object {
        // url to search active players by name :
        // https://suggest.svc.nhl.com/svc/suggest/v1/minactiveplayers/NAME/LIMIT
        const val SEARCH_BASE_URL = "https://suggest.svc.nhl.com/svc/suggest/v1/minactiveplayers/"
        const val LIMIT = 20
    }
}

interface SinglePlayerService {

    @GET("{player_id}/$STATS")
    suspend fun getSinglePlayerStats(
        @Path("player_id") playerId: Int,
        @Query(STATS) stats: String = THIS_YEAR
    ): Response<StatElement>

    companion object {
        // url to get single player stats (for single season)
        // https://statsapi.web.nhl.com/api/v1/people/PLAYER_ID/stats?stats=statsSingleSeason&season=20202021
        const val SINGLE_BASE_URL = "https://statsapi.web.nhl.com/api/v1/people/"
        const val STATS = "stats"
        const val THIS_YEAR = "statsSingleSeason&season=20202021"
    }
}