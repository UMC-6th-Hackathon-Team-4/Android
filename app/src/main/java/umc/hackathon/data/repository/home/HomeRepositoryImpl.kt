package umc.hackathon.data.repository.home

import umc.hackathon.data.api.HomeApi
import umc.hackathon.data.dto.ResponseBody
import umc.hackathon.data.dto.home.TreasureListResponse
import umc.hackathon.domain.repository.home.HomeRepository
import umc.hackathon.util.NetworkResult
import umc.hackathon.util.handleApi
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi
): HomeRepository {
    override suspend fun fetchTreasureList(): NetworkResult<TreasureListResponse> {
        return handleApi({api.fetchTreasureBoxList()}) {response: ResponseBody<TreasureListResponse> -> response.result}
    }

}