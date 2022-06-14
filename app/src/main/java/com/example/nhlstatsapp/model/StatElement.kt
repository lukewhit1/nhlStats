package com.example.nhlstatsapp.model

data class StatElement (
    val stats: List<PlayerStatItem>
)

data class PlayerStatItem (
    val splits: List<SplitStatItem>
)

data class SplitStatItem(
    val stat: SplitStat)

data class SplitStat (
    val timeOnIce: String,
    val assists: Long,
    val goals: Long,
    val pim: Long,
    val shots: Long,
    val games: Long,
    val hits: Long,
    val powerPlayGoals: Long,
    val powerPlayPoints: Long,
    val powerPlayTimeOnIce: String,
    val evenTimeOnIce: String,
    val penaltyMinutes: String,
    val faceOffPct: Double,
    val shotPct: Double,
    val gameWinningGoals: Long,
    val overTimeGoals: Long,
    val shortHandedGoals: Long,
    val shortHandedPoints: Long,
    val shortHandedTimeOnIce: String,
    val blocked: Long,
    val plusMinus: Long,
    val points: Long,
    val shifts: Long,
    val timeOnIcePerGame: String,
    val evenTimeOnIcePerGame: String,
    val shortHandedTimeOnIcePerGame: String,
    val powerPlayTimeOnIcePerGame: String
)
