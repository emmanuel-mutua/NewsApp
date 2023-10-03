package com.samkt.asternews.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samkt.asternews.presentation.home.components.ArticlesCard
import com.samkt.asternews.presentation.home.components.CategoryList
import com.samkt.asternews.presentation.home.components.HomeAppBar
import com.samkt.asternews.ui.theme.AsterNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsterHomeScreen(
    uiState: HomeUiState,
    onCardClick: () -> Unit,
    onShareButtonClick: (title: String, body: String) -> Unit,
    onMenuClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onCategoryClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            Column() {
                HomeAppBar(
                    onMenuClicked = onMenuClicked,
                    onSearchClicked = onSearchClicked,
                )
                Text(
                    text = "Top Stories for you",
                    modifier = Modifier.padding(start = 20.dp),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                CategoryList(
                    uiState = uiState,
                    onCategoryClick = {
                        onCategoryClick(it)
                    },
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            LazyColumn(content = {
                items(uiState.articles) { result ->
                    ArticlesCard(
                        onCardClick = onCardClick,
                        results = result,
                        onClickShareButton = onShareButtonClick,
                    )
                }
            })
        }
    }
}

@Preview
@Composable
fun AsterHomeScreenPrev() {
    AsterNewsTheme {
//        AsterHomeScreen()
    }
}
