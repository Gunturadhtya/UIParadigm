package com.experiment.uiparadigm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.experiment.uiparadigm.ui.theme.Blue100
import com.experiment.uiparadigm.ui.theme.Blue700
import com.experiment.uiparadigm.ui.theme.Orange50
import com.experiment.uiparadigm.ui.theme.Orange700
import com.experiment.uiparadigm.ui.theme.UIParadigmTheme

class DeclarativeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ── Cold Start Measurement ──────────────────────────────
        Log.d("COLD_START", "DeclarativeActivity onCreate started")

        // ── Declarative data transformation via functional operators ──
        val articles = StaticData.articles
            .partition { it.isTrending }
            .let { (trending, nonTrending) ->
                trending + nonTrending.sortedBy { it.readTimeMinutes }
            }

        setContent {
            UIParadigmTheme {
                NewsPortalScreen(articles = articles)
            }
        }

        Log.d("COLD_START", "DeclarativeActivity fully rendered")
    }
}

@Composable
fun NewsPortalScreen(articles: List<Article>) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Blue700)
                .padding(16.dp)
        ) {
            Text(
                text = "News Portal — Declarative",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // List
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(articles) { article ->
                ArticleCard(article = article)
            }
        }
    }
}

@Composable
fun ArticleCard(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Badges row
            Row {
                Surface(
                    color = Blue100,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = article.category,
                        color = Blue700,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                if (article.isTrending) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = Orange50,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = "🔥 Trending",
                            color = Orange700,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = article.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Summary
            Text(
                text = article.summary,
                fontSize = 14.sp,
                color = Color(0xFF757575),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Footer
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "By ${article.author} · ${article.publishDate}",
                    fontSize = 12.sp,
                    color = Color(0xFF9E9E9E),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${article.readTimeMinutes} min read",
                    fontSize = 12.sp,
                    color = Color(0xFF9E9E9E)
                )
            }
        }
    }
}
