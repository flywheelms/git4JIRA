package com.siliconvalleyoffice.git4jira.service.git

import com.siliconvalleyoffice.git4jira.constant.AUTHORIZATION_HEADER
import com.siliconvalleyoffice.git4jira.constant.EMPTY
import com.siliconvalleyoffice.git4jira.constant.NO_AUTHENTICATION_HEADER
import com.siliconvalleyoffice.git4jira.model.RequestInfo
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Credentials as OkHttpCredentials

class GitAuthInterceptor : Interceptor {

    private var authToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder();

        if (request.header(AUTHORIZATION_HEADER) == null && request.header(NO_AUTHENTICATION_HEADER) == null) {
            println("Attaching Header")
            authToken?.let { requestBuilder.addHeader(AUTHORIZATION_HEADER, it) }
        }
        return chain.proceed(requestBuilder.build())
    }

    fun setCredentials(requestInfo: RequestInfo?) {
        authToken = OkHttpCredentials.basic(requestInfo?.username ?: EMPTY, requestInfo?.password ?: EMPTY)
    }
}