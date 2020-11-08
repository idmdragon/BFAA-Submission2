package com.example.retrofit.api

import com.example.githubapp.api.AuthInterceptor
import com.example.githubapp.util.Constant.Companion.API_KEY
import com.example.githubapp.util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.logging.HttpLoggingInterceptor

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(AuthInterceptor(API_KEY))
                    .build()
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }

        val api by lazy {
            retrofit.create(GithubApi::class.java)
        }

    }
}





