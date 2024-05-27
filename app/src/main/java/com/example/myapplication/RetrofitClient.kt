package com.example.myapplication

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://tmmn.info/"

        fun get(): Retrofit {
            if (retrofit == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val headerInterceptor = Interceptor { chain ->
                    val originalRequest = chain.request()
                    val requestWithHeaders = originalRequest.newBuilder()
                        .header("branch", "1") // Add your headers here
                        /*.header("Another-Header-Name", "AnotherHeaderValue")*/
                        .build()
                    chain.proceed(requestWithHeaders)
                }
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor(headerInterceptor)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            }
            return retrofit!!
        }

        fun dismiss(){
            retrofit=null
        }

    }

    /*private class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request: Request = chain.request().newBuilder()
                .addHeader("BRANCH", "1")
                .build()
            return chain.proceed(request)
        }
    }*/

}