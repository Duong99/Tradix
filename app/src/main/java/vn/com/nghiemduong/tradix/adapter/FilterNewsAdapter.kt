package vn.com.nghiemduong.tradix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvFilterTradixBinding
import vn.com.nghiemduong.tradix.model.FilterTitle

class FilterNewsAdapter(var mListFilterNews: MutableList<FilterTitle>) :
    RecyclerView.Adapter<FilterNewsAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvFilterTradixBinding.bind(view)
        fun onBind(filterNews: FilterTitle) {
            binding.filterTradix = filterNews
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.mContext = parent.context
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_rcv_filter_tradix, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mListFilterNews.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var filterNews = mListFilterNews[position]
        filterNews?.let {
            holder.onBind(filterNews)

            holder.binding.tvTitleFilter.setOnClickListener {
                holder.binding.tvTitleFilter.background = mContext.resources.getDrawable(
                    R.drawable.bg_blue,
                    mContext.resources.newTheme()
                )
            }
        }
    }
}