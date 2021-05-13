package vn.com.nghiemduong.tradix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvFilterEditorNewsBinding
import vn.com.nghiemduong.tradix.model.FilterTitle

class FilterEditorNewsAdapter(var mListFilterEditorNews: MutableList<FilterTitle>) :
    RecyclerView.Adapter<FilterEditorNewsAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    private var mFilterEditorNews: FilterTitle? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvFilterEditorNewsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.mContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rcv_filter_editor_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mListFilterEditorNews.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filterEditorNews = mListFilterEditorNews[position]
        filterEditorNews.let {
            holder.binding.filterEditorNews = filterEditorNews

            holder.binding.tvFilterTitleEditorNews.setOnClickListener {
                this.mFilterEditorNews = filterEditorNews
                notifyDataSetChanged()
            }

            mFilterEditorNews?.let {
                if (mFilterEditorNews == filterEditorNews) {
                    holder.binding.tvFilterTitleEditorNews.background =
                        mContext.resources.getDrawable(R.drawable.bg_blue, mContext.theme)
                    holder.binding.tvFilterTitleEditorNews.setTextColor(Color.parseColor("#222222"))
                } else {
                    holder.binding.tvFilterTitleEditorNews.background = null
                    holder.binding.tvFilterTitleEditorNews.setTextColor(Color.parseColor("#858585"))
                }
            }
        }
    }
}