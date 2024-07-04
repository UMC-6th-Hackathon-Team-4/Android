package umc.hackathon.data.repository

import com.umc.ttoklip.data.model.naver.GeocodingResponse
import umc.hackathon.data.api.CreateApi
import umc.hackathon.data.api.NaverApi
import umc.hackathon.data.dto.create.TreasureResponse
import umc.hackathon.domain.repository.CreateRepository
import umc.hackathon.util.NetworkResult
import umc.hackathon.util.handleApi
import javax.inject.Inject

class CreateRepositoryImpl @Inject constructor(
  private val api: CreateApi
): CreateRepository {
//    override suspend fun createTresure(query: String): NetworkResult<TreasureResponse> {
//        return handleApi({api.createTreasure(query)}) {response: TreasureResponse -> response}
//        //ResponseBody<response> -> response.result
//    }
}

