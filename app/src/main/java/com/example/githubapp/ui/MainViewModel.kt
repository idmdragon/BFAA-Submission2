package com.example.githubapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.adapter.SearchAdapter
import com.example.githubapp.api.UsersResponse
import com.example.githubapp.model.UsersModel
import com.example.githubapp.util.Constant
import com.example.retrofit.api.RetrofitInstance

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class MainViewModel : ViewModel(){

    val listUsers = MutableLiveData<ArrayList<UsersModel>>()

    fun setSearchUser(keyword : String){
        var listItem = ArrayList<UsersModel>()
        RetrofitInstance.api.getSearch("$keyword").enqueue(object: Callback<UsersResponse> {
            override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
            ) {
                val response =  response.body()?.items
                if (response != null) {
                    Log.d(Constant.TAG, response.toString())
                    listItem = response
                }
                listUsers.postValue(listItem)
            }
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.d(Constant.TAG,"$t")
            }
        })
    }

    fun getSearchUser(): LiveData<ArrayList<UsersModel>> {
        return listUsers
    }


}