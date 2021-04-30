package vn.com.nghiemduong.tradix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvFilterTradixBinding
import vn.com.nghiemduong.tradix.model.FilterTradix

class FilterTradixAdapter(var mListFilterTradixs: MutableList<FilterTradix>) :
    RecyclerView.Adapter<FilterTradixAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvFilterTradixBinding.bind(view)

        fun onBind(filterTradix: FilterTradix) {
            binding.filterTradix = filterTradix
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_rcv_filter_tradix, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mListFilterTradixs.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filterTradix = mListFilterTradixs[position]

        filterTradix?.let {
            holder.onBind(filterTradix)

            holder.binding.tvTitleFilter.setOnClickListener {
                holder.binding.tvTitleFilter.background =
                    mContext.getDrawable(R.drawable.bg_item_filter_tradix_selected)

                holder.binding.tvTitleFilter.setTextColor(
                    mContext.resources.getColor(
                        R.color.white
                    )
                )
            }
        }
    }
}