package vn.com.nghiemduong.tradix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvFilterTradixBinding
import vn.com.nghiemduong.tradix.model.FilterTitle

class FilterTradixAdapter(var mListFilterTradixs: MutableList<FilterTitle>) :
    RecyclerView.Adapter<FilterTradixAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvFilterTradixBinding.bind(view)

        fun onBind(filterTradix: FilterTitle) {
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

        Log.d("Duong", "onBindViewHolder: $position")
        filterTradix?.let {
            holder.onBind(filterTradix)

            holder.binding.tvTitleFilter.setOnClickListener {
                filterTradix.isCheck = !filterTradix.isCheck
                if (filterTradix.isCheck) {
                    holder.binding.tvTitleFilter.background =
                        mContext.getDrawable(R.drawable.bg_item_filter_tradix_selected)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.binding.tvTitleFilter.setTextColor(
                            mContext.resources.getColor(
                                R.color.white, mContext.resources.newTheme()
                            )
                        )
                    }
                } else {
                    holder.binding.tvTitleFilter.background = null

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.binding.tvTitleFilter.setTextColor(
                            mContext.resources.getColor(
                                R.color.black_filter, mContext.resources.newTheme()
                            )
                        )
                    }
                }
            }
        }
    }
}