package com.example.nhlstatsapp.model

data class PointsLeadersResponse(
    val data: List<PlayerInfo>
)

data class PlayerInfo(
    val player: PlayerBio,
    val points: Int,
    val team: TeamInfo
)

data class PlayerBio(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val positionCode: String,
    val sweaterNumber: Int
)

data class TeamInfo(
    val id: Int,
    val fullName: String,
    val logos: List<LogoInfo>,
    val triCode: String
)

data class LogoInfo(
    val url: String
)