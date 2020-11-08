package com.example.retrofit.api


import com.example.githubapp.api.UsersResponse
import com.example.githubapp.model.UsersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    //search
    @GET("search/users")
    fun getSearch(
        @Query("q")
        search : String
    ): Call<UsersResponse>

    //getUser
    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Call<ArrayList<UsersModel>>


    /*
        @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Observable<List<UserSearch>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Observable<List<UserSearch>>
     */
}
