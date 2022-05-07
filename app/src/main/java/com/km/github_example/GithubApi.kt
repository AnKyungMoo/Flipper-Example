package com.km.github_example

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}")
    suspend fun getUserInfo(@Path("username") userName: String): UserInfo?
}
