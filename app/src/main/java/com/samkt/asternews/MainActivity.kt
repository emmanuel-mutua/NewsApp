package com.samkt.asternews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.samkt.asternews.presentation.home.AsterHomeScreen
import com.samkt.asternews.presentation.home.HomeViewModel
import com.samkt.asternews.ui.theme.AsterNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsterNewsTheme {
                val viewModel: HomeViewModel = hiltViewModel()
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                ) {
                    AsterHomeScreen(
                        uiState = viewModel.state.collectAsState().value,
                        onCardClick = { /*TODO*/ },
                        onShareButtonClick = { title, body ->
                            viewModel.shareNews(title, body, this)
                        },
                        onMenuClicked = { /*TODO*/ },
                        onSearchClicked = { /*TODO*/ },
                        onCategoryClick = {
                            viewModel.setCategorySelected(it)
                        },
                    )
                }
            }
        }
    }
}
