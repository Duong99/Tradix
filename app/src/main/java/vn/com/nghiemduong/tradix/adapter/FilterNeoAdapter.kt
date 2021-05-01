package vn.com.nghiemduong.tradix.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvFilterNeoBinding
import vn.com.nghiemduong.tradix.databinding.ItemRcvFilterTradixBinding
import vn.com.nghiemduong.tradix.model.FilterTitle

class FilterNeoAdapter(var mListFilterNeo: MutableList<FilterTitle>) :
    RecyclerView.Adapter<FilterNeoAdapter.ViewHolder>() {
    private lateinit var mContext: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvFilterNeoBinding.bind(view)

        fun onBind(neoFilter: FilterTitle) {
            binding.filterNeo = neoFilter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.mContext = parent.context
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_rcv_filter_neo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mListFilterNeo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filterTradix = mListFilterNeo[position]

        filterTradix?.let {
            holder.onBind(filterTradix)

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

            holder.binding.tvTitleFilter.setOnClickListener {
                filterTradix.isCheck = !filterTradix.isCheck
                notifyDataSetChanged()
            }
        }
    }
}