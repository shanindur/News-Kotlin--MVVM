package com.app.shanindu.news.repository

import com.app.shanindu.news.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface NewsService {
    var HTTPS_API_GITHUB_URL: String
        get() = "https://api.github.com/"
        set(value) = TODO()


    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Article>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Article>

}