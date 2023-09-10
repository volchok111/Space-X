package com.volchok.space_x.library.api.data

import com.volchok.space_x.library.api.domain.RemoteRepository
import com.volchok.space_x.library.api.model.company.CompanyModel
import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.api.model.rocket.RocketItem
import com.volchok.space_x.library.data.model.Data

class RocketRepository(
    private val rocketApi: RocketApi
) : RemoteRepository {

    override suspend fun getRockets(): Data<List<RocketItem>> {
        return try {
            val result = rocketApi.getRockets()
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(cause = ex)
        }
    }

    override suspend fun getRocketInfo(id: String): Data<RocketDetailsModel> {
        return try {
            val result = rocketApi.getRocketInfo(id)
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(cause = ex)
        }
    }

    override suspend fun getCompany(): Data<CompanyModel> {
        return try {
            val result = rocketApi.getCompany()
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(cause = ex)
        }
    }
}