package umc.hackathon.data.dto.create

import com.google.gson.annotations.SerializedName

data class TreasureResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("status") val status: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)
