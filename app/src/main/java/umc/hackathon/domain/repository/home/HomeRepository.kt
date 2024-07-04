package umc.hackathon.domain.repository.home

import umc.hackathon.data.dto.home.TreasureListResponse
import umc.hackathon.util.NetworkResult

interface HomeRepository {
    suspend fun fetchTreasureList(): NetworkResult<TreasureListResponse>
}