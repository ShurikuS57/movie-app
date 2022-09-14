package ru.shurikus.coreNetwork.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiQueryInterceptor(
    private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originHttpUrl = original.url
        val url = originHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val request = original.newBuilder()
            .url(url).build()
        return chain.proceed(request)
    }
}
