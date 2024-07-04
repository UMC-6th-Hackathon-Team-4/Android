package umc.hackathon.data.repository

import com.umc.ttoklip.data.model.naver.GeocodingResponse
import umc.hackathon.data.api.NaverApi
import umc.hackathon.domain.repository.NaverRepository
import umc.hackathon.util.NetworkResult
import umc.hackathon.util.handleApi
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val api: NaverApi
) : NaverRepository {
    override suspend fun fetchGeocoding(query: String): NetworkResult<GeocodingResponse> {
        return handleApi({api.fetchGeocoding(query)}) {response: GeocodingResponse -> response}
        //ResponseBody<response> -> response.result
    }

}