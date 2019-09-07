package com.song2.unifarm

import com.song2.unifarm.Data.CollectPopularProgramData

data class GetPopularProgram(
    val data: ArrayList<CollectPopularProgramData>,
    val message: String,
    val status: Int
)