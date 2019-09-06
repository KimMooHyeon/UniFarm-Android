package com.song2.unifarm.Network.GET

data class GetProgramsResonse(
    val status: Int,
    val message: String,
    val data: ArrayList<ProgramData>
)