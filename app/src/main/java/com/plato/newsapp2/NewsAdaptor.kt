package com.plato.newsapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.FieldPosition

class NewsAdaptor(private val listener:OneNewsClick) : RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>() {

    private val item = ArrayList<News>()

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.tittle)
        val author: TextView = itemView.findViewById(R.id.author)
        val image: ImageView = itemView.findViewById(R.id.image)
        //val items: ImageView = itemView.findViewById(R.id.list_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = item[position]
        holder.titleView.text = currentNews.tittle
        holder.author.text = currentNews.author

        Glide.with(holder.itemView.context).load(currentNews.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun updateData(newData:ArrayList<News>){
        item.clear()
        item.addAll(newData)

        notifyDataSetChanged()
    }

    // now we will learn how to open a chrome tab when news clicked to read full news
    interface OneNewsClick{
        fun  onClicked(news: News)
    }
}