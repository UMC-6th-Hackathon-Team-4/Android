package umc.hackathon.domain.repository

import com.umc.ttoklip.data.model.naver.GeocodingResponse
import umc.hackathon.util.NetworkResult

interface NaverRepository {
    suspend fun fetchGeocoding(query: String): NetworkResult<GeocodingResponse>
}