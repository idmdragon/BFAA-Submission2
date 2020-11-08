package com.example.githubapp.api

import com.example.githubapp.model.UsersModel


data class UsersResponse(
    val incomplete_results: Boolean,
    val items: ArrayList<UsersModel>,
    val total_count: Int
)