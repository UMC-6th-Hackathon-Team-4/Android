package umc.hackathon.data.dto.create

data class TreasureRequest (
    val userId: Int,
    val deadline: String,
    val status: String,
    val title: String,
    val body: String,
    val latitude: Double,
    val longitude: Double
)