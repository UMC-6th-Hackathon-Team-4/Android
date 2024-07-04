package umc.hackathon.domain.model

import android.net.Uri
import java.io.Serializable

data class Treasure(
    val index: Int,
    val uri: Uri,
    val text: String,
    val isWritten: Boolean = false
): Serializable
