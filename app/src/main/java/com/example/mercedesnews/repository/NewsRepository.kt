package com.example.mercedesnews.repository

import com.example.mercedesnews.api.RetrofitInstance
import com.example.mercedesnews.db.ArticleDatabase
import com.example.mercedesnews.models.Article

class NewsRepository(
    val db: ArticleDatabase
)  {

    suspend fun getTopNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopNews(countryCode, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}