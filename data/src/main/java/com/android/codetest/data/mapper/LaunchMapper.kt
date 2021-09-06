package com.android.codetest.data.mapper

import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.LaunchSite
import com.android.codetest.domain.model.Ship
import com.chohtet.android.codetest.spacex.GetLaunchListQuery

class LaunchMapper : ModelMapper<GetLaunchListQuery.Launch, Launch> {
    override fun map(entity: GetLaunchListQuery.Launch): Launch {
        return Launch(
            id = entity.id ?: "",
            mission_id = entity.mission_id ?: listOf(),
            details = entity.details ?: "",
            launch_date_utc = entity.launch_date_utc.toString(),
            ships = entity.ships?.let { transform(it) } ?: listOf(),
            launch_site = transform(entity.launch_site)
        )
    }

    private fun transform(entity: List<GetLaunchListQuery.Ship?>): List<Ship> {
        return entity.map {
            Ship(it?.image ?: "")
        }
    }

    private fun transform(entity: GetLaunchListQuery.Launch_site?): LaunchSite {
        return LaunchSite(entity?.__typename, entity?.site_name_long)
    }


}
