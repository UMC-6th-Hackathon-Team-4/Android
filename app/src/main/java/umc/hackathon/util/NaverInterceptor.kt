package umc.hackathon.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import umc.hackathon.MainApplication
import umc.hackathon.R

class NaverInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("X-NCP-APIGW-API-KEY-ID", MainApplication.getString(R.string.naver_client_key))
            .addHeader("X-NCP-APIGW-API-KEY", MainApplication.getString(R.string.naver_client_secret_key))
            .build()

        proceed(newRequest)
    }
}
