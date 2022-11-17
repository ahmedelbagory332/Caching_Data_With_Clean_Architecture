package com.example.presentation.adapter



import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.RestaurantModel
import com.example.presentation.R
import javax.inject.Inject

class RestaurantAdapter @Inject constructor(private val ctx:Application) :
    ListAdapter<RestaurantModel, RestaurantAdapter.RestaurantViewHolder>(RestaurantDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)

        Glide.with(ctx)
            .load(restaurant.logo)
            .into( holder.logo)

        holder.textViewName.text = restaurant.name
        holder.textViewType.text = restaurant.type
        holder.textViewAddress.text = restaurant.address
    }


    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName : TextView = itemView.findViewById(R.id.text_view_name)
        val textViewType : TextView = itemView.findViewById(R.id.text_view_type)
        val textViewAddress : TextView = itemView.findViewById(R.id.text_view_address)
        val logo : ImageView = itemView.findViewById(R.id.image_view_logo)

    }




}

object RestaurantDiffCallback : DiffUtil.ItemCallback<RestaurantModel>() {
    override fun areItemsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean {
        return oldItem.name == newItem.name
    }
}