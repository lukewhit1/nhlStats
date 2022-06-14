package com.example.nhlstatsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerDisplayStats(
    var playerId: Int = 0,
    var fullName: String = "",
    var goals: Int = 0,
    var assists: Int = 0,
    var points: Int = 0,
    var teamName: String = "",
    //TODO: try to find pics of players
    var photograph: String = "https://1000logos.net/wp-content/uploads/2017/05/NHL-Logo.png",
    var height: String = "",
    var weight: String = "",
    var birthday: String = ""
): Parcelable
