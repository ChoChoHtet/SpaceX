package com.chohtet.android.codetest.spacex.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class LaunchEntity(
    val id:String?,
    val siteName:String?,
    val details:String?,
    val missionId:List<String?>?
) : Parcelable