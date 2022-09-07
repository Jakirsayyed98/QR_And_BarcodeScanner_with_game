package com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses

data class Game(
    val assets: Assets,
    val categories: Categories,
    val code: String,
    val colorMuted: String,
    val colorVibrant: String,
    val description: Description,
    val gamePlays: Int,
    val gamePreviews: GamePreviews,
    val height: Int,
    val isPortrait: Boolean,
    val name: Name,
    val numberOfRatings: Int,
    val privateAllowed: Boolean,
    val rating: Double,
    val tags: Tags,
    val url: String,
    val width: Int
)