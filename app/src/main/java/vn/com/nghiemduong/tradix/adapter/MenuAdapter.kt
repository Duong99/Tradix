package vn.com.nghiemduong.tradix.adapter


import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ItemRcvViewTypeMenuItemBinding
import vn.com.nghiemduong.tradix.databinding.ItemRcvViewTypeMenuLineBinding
import vn.com.nghiemduong.tradix.databinding.ItemRcvViewTypeMenuSettingBinding
import vn.com.nghiemduong.tradix.databinding.ItemRcvViewTypeMenuTitleBinding
import vn.com.nghiemduong.tradix.model.Menu

const val VIEW_TYPE_MENU_SETTING = 1
const val VIEW_TYPE_MENU_ITEM = 2
const val VIEW_TYPE_MENU_TITLE = 3
const val VIEW_TYPE_MENU_LINE = 4

class MenuAdapter(var mListMenu: MutableList<Menu>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            VIEW_TYPE_MENU_SETTING -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rcv_view_type_menu_setting, parent, false)
                SettingViewHolder(view)
            }
            VIEW_TYPE_MENU_TITLE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rcv_view_type_menu_title, parent, false)
                TitleViewHolder(view)
            }

            VIEW_TYPE_MENU_ITEM -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rcv_view_type_menu_item, parent, false)
                ItemViewHolder(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rcv_view_type_menu_line, parent, false)
                LineViewHolder(view)
            }
        }
    }

    override fun getItemCount() = mListMenu.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menu = mListMenu[position]
        menu?.let {
            when (holder.itemViewType) {
                VIEW_TYPE_MENU_SETTING -> {
                    val bindingSetting = holder as SettingViewHolder
                    bindingSetting.bindingSetting.menu = menu
                }

                VIEW_TYPE_MENU_TITLE -> {
                    val bindingTitle = holder as TitleViewHolder
                    bindingTitle.bindingTitle.menu = menu
                }

                VIEW_TYPE_MENU_ITEM -> {
                    val bindingItem = holder as ItemViewHolder
                    bindingItem.bindingItem.menu = menu
                }

                else -> {

                }
            }
        }
    }

    override fun getItemViewType(position: Int) = mListMenu[position].viewType

    class SettingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bindingSetting = ItemRcvViewTypeMenuSettingBinding.bind(view)
    }

    class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bindingTitle = ItemRcvViewTypeMenuTitleBinding.bind(view)

    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bindingItem = ItemRcvViewTypeMenuItemBinding.bind(view)
    }

    class LineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}