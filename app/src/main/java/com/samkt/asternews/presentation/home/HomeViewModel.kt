package com.samkt.asternews.presentation.home

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.asternews.data.asterNewsDtos.Result
import com.samkt.asternews.data.asterNewsRepository.AsterNewsRepository
import com.samkt.asternews.presentation.home.components.ArticlesCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val asterNewsRepository: AsterNewsRepository,
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        viewModelScope.launch {
            val allArticles = asterNewsRepository.getAllArticles()
            allArticles.collectLatest { allArticles ->
                _state.update {
                    it.copy(
                        articles = allArticles.articles.results,
                    )
                }
            }
        }
    }

    private fun getArticlesByCategory(keyword: String) {
        viewModelScope.launch {
            val allArticles = asterNewsRepository.getArticlesOnCategory(keyword)
            allArticles.collectLatest { allArticles ->
                _state.update {
                    it.copy(
                        articles = allArticles.articles.results,
                    )
                }
            }
        }
    }

    fun shareNews(title: String, body: String, activity: Activity) {
        val intent = Intent(Intent.ACTION_SEND)
            .putExtra(Intent.EXTRA_SUBJECT, title)
            .putExtra(Intent.EXTRA_TEXT, body)
        intent.type = ("text/plain")

        val chooserTitle = "Share Via"
        val chooserIntent = Intent.createChooser(intent, chooserTitle)
        activity.startActivity(chooserIntent)
    }

    fun setCategorySelected(categoryName: String) {
        if (categoryName != ArticlesCategories.ALL.value) {
            getArticlesByCategory(categoryName)
        } else {
            getAllArticles()
        }
        _state.update {
            it.copy(
                categoryNameSelected = categoryName,
            )
        }
    }
}

data class HomeUiState(
    val articles: List<Result> = emptyList(),
    val categories: List<ArticlesCategories> = emptyList(),
    val categoryNameSelected: String = ArticlesCategories.ALL.value,
)
