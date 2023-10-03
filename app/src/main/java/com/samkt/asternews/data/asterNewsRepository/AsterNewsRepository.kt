package com.samkt.asternews.data.asterNewsRepository

import com.samkt.asternews.data.asterNewsDtos.AllArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AsterNewsRepository {
    suspend fun getAllArticles(): Flow<AllArticles>
    suspend fun getArticlesOnCategory(keyword: String): Flow<AllArticles>
}

class AsterNewsRepositoryImpl @Inject constructor(
    private val newsApi: AsterNewsApiService,
) : AsterNewsRepository {
    override suspend fun getAllArticles(): Flow<AllArticles> = flow {
        val allArticles = newsApi.getAllArticles()
        emit(allArticles)
    }

    override suspend fun getArticlesOnCategory(keyword: String): Flow<AllArticles> = flow {
        val preferredArticles = newsApi.getArticlesOnCategory(keyword = keyword)
        emit(preferredArticles)
    }
}
