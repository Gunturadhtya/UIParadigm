package com.experiment.uiparadigm

data class Article(
    val id: Int,
    val title: String,
    val category: String,
    val summary: String,
    val author: String,
    val publishDate: String,
    val readTimeMinutes: Int,
    val isTrending: Boolean
)

object StaticData {

    val articles = listOf(
        Article(1,  "NixOS Reaches 25.11 Milestone",       "Technology", "The NixOS community celebrates the 25.11 release with improved tooling.", "Alice",   "2025-06-01", 4, true),
        Article(2,  "Jetpack Compose Matures in 2025",      "Android",    "Compose becomes the default UI toolkit for new Android projects.",         "Bob",     "2025-06-02", 5, true),
        Article(3,  "Distrobox Simplifies Linux Workflows",  "Linux",      "Distrobox allows seamless container-based development on any distro.",     "Charlie", "2025-06-03", 3, false),
        Article(4,  "Kotlin 2.0 Compiler Improvements",     "Android",    "The new K2 compiler delivers significant build speed gains.",              "Diana",   "2025-06-04", 6, true),
        Article(5,  "RecyclerView vs LazyColumn Benchmark",  "Android",    "A detailed performance comparison of the two list implementations.",       "Eve",     "2025-06-05", 7, false),
        Article(6,  "Gradle 9.0 Released",                  "Technology", "Gradle 9.0 introduces isolated projects and faster incremental builds.",   "Frank",   "2025-06-06", 4, false),
        Article(7,  "OpenJDK 21 LTS in Production",         "Java",       "Enterprises adopt OpenJDK 21 for long-term stability and performance.",    "Grace",   "2025-06-07", 5, true),
        Article(8,  "Declarative UI: The Future of Mobile",  "Mobile",     "Industry analysts predict declarative paradigms will dominate by 2027.",   "Henry",   "2025-06-08", 6, true),
        Article(9,  "Android Studio Profiler Deep Dive",     "Android",    "How to measure CPU, RAM, and energy consumption accurately.",              "Iris",    "2025-06-09", 8, false),
        Article(10, "Cold Start Optimization Techniques",    "Performance","Strategies to reduce cold start time below 500ms on mid-range devices.",   "Jack",    "2025-06-10", 7, false)
    )

    fun getTrendingArticles(): List<Article> =
        articles.filter { it.isTrending }

    fun getArticlesByCategory(category: String): List<Article> =
        articles.filter { it.category == category }

    fun getSortedByReadTime(): List<Article> =
        articles.sortedBy { it.readTimeMinutes }
}
