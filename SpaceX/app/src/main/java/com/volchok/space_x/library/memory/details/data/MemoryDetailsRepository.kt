package com.volchok.space_x.library.memory.details.data

import com.volchok.space_x.library.memory.details.domain.DetailsRepository

class MemoryDetailsRepository : DetailsRepository {
    override var selectedRocketId: String = ""
}