package com.android.codetest.data.mapper

import com.android.codetest.domain.model.Mission
import com.chohtet.android.codetest.spacex.MissionQuery

class MissionMapper : ModelMapper<MissionQuery.Mission, Mission> {
    override fun map(entity: MissionQuery.Mission): Mission {
        return Mission(
            entity.id,
            entity.name,
            entity.twitter,
            entity.wikipedia
        )
    }
}