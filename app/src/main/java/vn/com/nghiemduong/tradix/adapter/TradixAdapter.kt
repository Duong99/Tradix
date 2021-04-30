package vn.com.nghiemduong.tradix.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvTradixBinding
import vn.com.nghiemduong.tradix.model.Tradix

class TradixAdapter(var mListTradixs: MutableList<Tradix>) :
    RecyclerView.Adapter<TradixAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRcvTradixBinding.bind(view)

        fun onBind(tradix: Tradix) {
            binding.tradix = tradix
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_rcv_tradix, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mListTradixs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tradix = mListTradixs[position]
        tradix?.let {
            holder.onBind(tradix)
        }
    }
}