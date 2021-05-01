package vn.com.nghiemduong.tradix.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvNewsBinding
import vn.com.nghiemduong.tradix.model.News

class NewsAdapter(var mListNews: MutableList<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvNewsBinding.bind(view)

        fun onBind(new: News) {
            binding.news = new
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rcv_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mListNews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = mListNews[position]
        news?.let {
            holder.onBind(news)
        }
    }
}