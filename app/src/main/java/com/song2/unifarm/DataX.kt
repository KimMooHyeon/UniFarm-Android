package com.song2.unifarm

import com.song2.unifarm.Data.CommingActivityData
import com.song2.unifarm.Data.EndActivityData

data class DataX(
    val programEnd: ArrayList<EndActivityData>,
    val programIng: ArrayList<CommingActivityData>
)