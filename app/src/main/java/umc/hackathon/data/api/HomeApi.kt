package umc.hackathon.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import umc.hackathon.data.dto.ResponseBody
import umc.hackathon.data.dto.home.TreasureListResponse

interface HomeApi {
    @GET("treasurebox/list")
    suspend fun fetchTreasureBoxList(@Query("treasureId") treasureId: Long = 1): Response<ResponseBody<TreasureListResponse>>
}