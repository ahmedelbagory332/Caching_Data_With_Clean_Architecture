package com.example.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.adapter.RestaurantAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: RestaurantViewModel by viewModels()
    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter
    lateinit var restaurantsList:RecyclerView
    lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         restaurantsList  = findViewById(R.id.recycler_view)
         progressBar = findViewById(R.id.progress_bar)

        restaurantsList.adapter = restaurantAdapter

        viewModel.getRestaurant("40")
        viewModel.restaurants.observe(this, Observer {

              if(it.isLoading){
                  progressBar.visibility = View.VISIBLE
              }else if(it.restaurants.isNotEmpty()){
                  restaurantAdapter.submitList(it.restaurants)
                  progressBar.visibility = View.GONE
              }else{
                  Log.d("bego error",it.error)
                  progressBar.visibility = View.GONE
              }

        })

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {

            R.id.show_20 -> {
                restaurantAdapter.submitList(listOf())
                viewModel.getRestaurant("20")
            }
            R.id.show_30 -> {
                restaurantAdapter.submitList(listOf())
                viewModel.getRestaurant("30")

            }
        R.id.default_menu -> {
            restaurantAdapter.submitList(listOf())
            viewModel.getRestaurant("40")
            }
        }
        return true
    }

}