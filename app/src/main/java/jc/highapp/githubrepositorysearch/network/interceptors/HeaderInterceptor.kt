package jc.highapp.githubrepositorysearch.network.interceptors

import jc.highapp.githubrepositorysearch.BuildConfig
import jc.highapp.githubrepositorysearch.constants.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request

        try {
            newRequest = request.newBuilder()
                .addHeader(Constants.HEADER_AUTHONRIZATION, "token " + BuildConfig.GITHUB_ACCESS_TOKEN)
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            return chain.proceed(request)
        }

        return chain.proceed(newRequest)
    }

}