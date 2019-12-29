package com.app.shanindu.news.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.shanindu.news.R
import com.app.shanindu.news.model.News
import com.balysv.materialripple.MaterialRippleLayout
import com.squareup.picasso.Picasso


class NewsAdapter(val context: Context?, val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null


    private var lastPosition = -1
    private var on_attach = true

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, obj: News)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var tvId: TextView
        var txtHeadline: TextView
        var imgNews: ImageView
        var layout: MaterialRippleLayout


        init {
            layout = view.findViewById(R.id.layout_parent)
            txtHeadline = view.findViewById(R.id.txt_headline)
//            tvName = view.findViewById(R.id.tv_name)
            imgNews = view.findViewById(R.id.img_news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = newsList[position]
        holder.layout.setOnClickListener(View.OnClickListener { view ->
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemClick(view, position, news)
            }
        })

//        holder?.tvId?.setText(news.author)
        holder?.txtHeadline?.setText(news.title)

        Picasso
                .get() // give it the context
                .load(news.urlToImage) // load the image
                .into(holder?.imgNews)


    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                on_attach = false
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }


}
