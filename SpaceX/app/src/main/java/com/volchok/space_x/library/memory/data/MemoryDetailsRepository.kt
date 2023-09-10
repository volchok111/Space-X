package com.volchok.space_x.library.memory.data

import com.volchok.space_x.library.memory.domain.DetailsRepository

class MemoryDetailsRepository : DetailsRepository {
    override var selectedRocketId: String = ""
}