package vn.com.nghiemduong.tradix.ui.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vn.com.nghiemduong.tradix.adapter.FilterNeoAdapter
import vn.com.nghiemduong.tradix.databinding.FragmentCoinBinding
import vn.com.nghiemduong.tradix.model.FilterTitle

class CoinFragment : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!
    private lateinit var mListFilterNeo: MutableList<FilterTitle>
    private lateinit var mNeoAdapter: FilterNeoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinBinding.inflate(inflater, container, false)

        addListFilterNeo()
        setUpRcvFilterNeo()

        binding.cvAlarm.setOnClickListener {
            if (binding.cvAlarm.cardBackgroundColor ==
                ColorStateList.valueOf(Color.parseColor("#FF018786"))
            ) {
                binding.cvAlarm.setCardBackgroundColor(
                    ColorStateList.valueOf(Color.parseColor("#101010"))
                )
            } else {
                binding.cvAlarm.setCardBackgroundColor(
                    ColorStateList.valueOf(Color.parseColor("#FF018786"))
                )
            }
        }
        return binding.root
    }

    private fun setUpRcvFilterNeo() {
        binding.rcvFilterNeo.setHasFixedSize(true)
        binding.rcvFilterNeo.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mNeoAdapter = FilterNeoAdapter(mListFilterNeo)
        binding.rcvFilterNeo.adapter = mNeoAdapter
    }

    private fun addListFilterNeo() {
        mListFilterNeo = mutableListOf(
            FilterTitle("General"),
            FilterTitle("Technical Section"),
            FilterTitle("Markets"),
            FilterTitle("Charts"),
            FilterTitle("General"),
            FilterTitle("Technical Section"),
            FilterTitle("Markets"),
            FilterTitle("Charts")
        )
    }
}