package com.android.codetest.data.mapper

import com.android.codetest.domain.model.LaunchSite
import com.chohtet.android.codetest.spacex.GetLaunchListQuery

class LaunchSiteMapper : ModelMapper<GetLaunchListQuery.Launch_site, LaunchSite> {
    override fun map(entity: GetLaunchListQuery.Launch_site): LaunchSite {
        return LaunchSite(entity.__typename, entity.site_name_long)
    }
}