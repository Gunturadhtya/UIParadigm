package com.experiment.uiparadigm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImperativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ── Cold Start Measurement ──────────────────────────────
        Log.d("COLD_START", "ImperativeActivity onCreate started")

        setContentView(R.layout.activity_imperative)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ── Imperative data transformation via for-loops ────────
        val allArticles = StaticData.articles
        val trendingList = mutableListOf<Article>()
        val nonTrendingList = mutableListOf<Article>()

        for (article in allArticles) {
            if (article.isTrending) {
                trendingList.add(article)
            } else {
                nonTrendingList.add(article)
            }
        }

        // Sort non-trending by read time manually
        val sortedNonTrending = mutableListOf<Article>()
        val temp = nonTrendingList.toMutableList()
        while (temp.isNotEmpty()) {
            var minIndex = 0
            for (i in 1 until temp.size) {
                if (temp[i].readTimeMinutes < temp[minIndex].readTimeMinutes) {
                    minIndex = i
                }
            }
            sortedNonTrending.add(temp.removeAt(minIndex))
        }

        val finalList = mutableListOf<Article>()
        for (article in trendingList) finalList.add(article)
        for (article in sortedNonTrending) finalList.add(article)

        recyclerView.adapter = ArticleAdapter(finalList)

        Log.d("COLD_START", "ImperativeActivity fully rendered")
    }
}

// ── RecyclerView Adapter ────────────────────────────────────────────
class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvTrending: TextView = itemView.findViewById(R.id.tvTrending)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvSummary: TextView = itemView.findViewById(R.id.tvSummary)
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val tvReadTime: TextView = itemView.findViewById(R.id.tvReadTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        holder.tvCategory.text = article.category
        holder.tvTitle.text = article.title
        holder.tvSummary.text = article.summary
        holder.tvAuthor.text = "By ${article.author} · ${article.publishDate}"
        holder.tvReadTime.text = "${article.readTimeMinutes} min read"

        if (article.isTrending) {
            holder.tvTrending.visibility = View.VISIBLE
        } else {
            holder.tvTrending.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = articles.size
}
