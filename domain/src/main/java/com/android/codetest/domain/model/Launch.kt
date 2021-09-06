package com.android.codetest.domain.model

import kotlinx.android.parcel.Parcelize

 class Launch(
    val id: String,
    val ships: List<Ship>,
    val launch_site: LaunchSite,
    val details: String?,
    val mission_id: List<String?>,
    val launch_date_utc: String
)