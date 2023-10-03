package com.samkt.asternews.data.asterNewsRepository

import com.samkt.asternews.BuildConfig
import com.samkt.asternews.data.asterNewsDtos.AllArticles
import retrofit2.http.POST
import retrofit2.http.Query

interface AsterNewsApiService {
    @POST("v1/article/getArticles")
    suspend fun getAllArticles(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = "eng",
    ): AllArticles

    @POST("v1/article/getArticles")
    suspend fun getArticlesOnCategory(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = "eng",
        @Query("keyword") keyword: String,
    ): AllArticles
}
