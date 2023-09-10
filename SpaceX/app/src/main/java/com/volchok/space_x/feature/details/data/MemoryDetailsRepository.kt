package com.volchok.space_x.feature.details.data

import com.volchok.space_x.feature.details.domain.DetailsRepository

class MemoryDetailsRepository : DetailsRepository {
    override var selectedRocketId: String = ""
}