package com.example.githubapp.ui

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.adapter.SearchAdapter
import com.example.githubapp.util.Constant.Companion.TAG

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var mainViewModel: MainViewModel
    private lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.userRV)
        progressBar = findViewById(R.id.progressBar)

        image = findViewById(R.id.iv_main)


        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)




        mainViewModel.getSearchUser().observe(this, Observer { userItems ->
            if (userItems != null) {
                adapter = SearchAdapter(userItems)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
                showLoading(false)
                if (adapter.itemCount == 0) {
                    image.visibility = View.VISIBLE
                } else {
                    image.visibility = View.INVISIBLE
                }

            }
        })


        recyclerView.layoutManager = LinearLayoutManager(this)




    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        progressBar = findViewById(R.id.progressBar)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val search = menu?.findItem(R.id.search)
        val searchView = search?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                mainViewModel.setSearchUser(query)

                if (query.isEmpty()) {
                    image.visibility = View.VISIBLE
                }
                getData()
                showLoading(true)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mainViewModel.setSearchUser(newText)

                if (newText.isEmpty()) {
                    image.visibility = View.VISIBLE
                }
                getData()
                showLoading(true)
                return false
            }
        })
        return true
    }


    fun getData(){
        mainViewModel.getSearchUser().observe(this, Observer { userItems ->
            if (userItems != null) {
                adapter = SearchAdapter(userItems)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
                showLoading(false)
                if (adapter.itemCount == 0) {
                    image.visibility = View.VISIBLE
                } else {
                    image.visibility = View.INVISIBLE
                }

            }
        })


        recyclerView.layoutManager = LinearLayoutManager(this)
        mainViewModel.getSearchUser().observe(this, Observer { userItems ->
            if (userItems != null) {
                adapter = SearchAdapter(userItems)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
                showLoading(false)
                if (adapter.itemCount == 0) {
                    image.visibility = View.VISIBLE
                } else {
                    image.visibility = View.INVISIBLE
                }

            }
        })


        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}


