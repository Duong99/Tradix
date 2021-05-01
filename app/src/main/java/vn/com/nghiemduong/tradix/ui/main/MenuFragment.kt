package vn.com.nghiemduong.tradix.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.adapter.*

import vn.com.nghiemduong.tradix.databinding.FragmentMenuBinding
import vn.com.nghiemduong.tradix.model.Menu


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var mListMenu: MutableList<Menu>
    private lateinit var mMenuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        addListMenu()
        setUpRcvMenu()
        return binding.root
    }

    private fun addListMenu() {
        mListMenu = mutableListOf(
            Menu(0, "", VIEW_TYPE_MENU_LINE),
            Menu(R.drawable.icons_8_alarm, "Alerts", VIEW_TYPE_MENU_SETTING),
            Menu(R.drawable.icons_8_left_and_right_arrows, "Predictions", VIEW_TYPE_MENU_SETTING),
            Menu(R.drawable.icons_8_pin, "Saved elements", VIEW_TYPE_MENU_SETTING),
            Menu(R.drawable.icons_8_no_entry, "Remove Ads", VIEW_TYPE_MENU_SETTING),
            Menu(0, "", VIEW_TYPE_MENU_LINE),
            Menu(0, "Tools", VIEW_TYPE_MENU_TITLE),
            Menu(R.drawable.icons_8_profit_2, "Select Stocks", VIEW_TYPE_MENU_ITEM),
            Menu(R.drawable.icons_8_swap, "Currency Exchange", VIEW_TYPE_MENU_ITEM),
            Menu(R.drawable.icons_8_video_call, "Webinar", VIEW_TYPE_MENU_ITEM),
            Menu(R.drawable.icons_8_rent, "Best Broker", VIEW_TYPE_MENU_ITEM),
            Menu(0, "", VIEW_TYPE_MENU_LINE),
            Menu(0, "Markets", VIEW_TYPE_MENU_TITLE),
            Menu(R.drawable.icons_8_profit, "Select Stocks", VIEW_TYPE_MENU_ITEM)
        )
    }

    private fun setUpRcvMenu() {
        binding.rcvMenu.setHasFixedSize(true)
        binding.rcvMenu.layoutManager = LinearLayoutManager(context)
        mMenuAdapter = MenuAdapter(mListMenu)
        binding.rcvMenu.adapter = mMenuAdapter
    }
}