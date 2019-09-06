package com.song2.unifarm.Network.GET

data class ProgramData (
    val program: Program,
    val programDates: ArrayList<ProgramDate>,
    val keywords: ArrayList<Keyword>
)