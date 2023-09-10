package com.volchok.space_x.library.rockets.data

import com.volchok.space_x.library.rockets.domain.DetailsRepository

class MemoryDetailsRepository : DetailsRepository {
    override var selectedRocketId: String = ""
}