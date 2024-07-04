package umc.hackathon.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import umc.hackathon.data.dto.ResponseBody
import umc.hackathon.data.dto.create.TreasureRequest
import umc.hackathon.data.dto.create.TreasureResponse

interface CreateApi {
    @GET("/tresurebox")
    suspend fun createTreasure(@Body request: TreasureRequest ): Response<ResponseBody<TreasureResponse>>
}