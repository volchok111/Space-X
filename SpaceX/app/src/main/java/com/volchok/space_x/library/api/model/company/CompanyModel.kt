package com.volchok.space_x.library.api.model.company

data class CompanyModel(
    val ceo: String,
    val coo: String,
    val employees: Int,
    val founded: Int,
    val headquarters: Headquarters,
    val id: String,
    val links: Links,
    val name: String,
    val summary: String,
    val valuation: Long,
)