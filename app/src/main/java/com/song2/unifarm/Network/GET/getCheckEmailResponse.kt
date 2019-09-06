package com.song2.unifarm.Network.GET

import com.song2.unifarm.Data.userData

data class getCheckEmailResponse(var status: Int,
                              var message: String,
                              var data: userData?)
