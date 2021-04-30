package vn.com.nghiemduong.tradix.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.adapter.FilterTradixAdapter
import vn.com.nghiemduong.tradix.adapter.TradixAdapter
import vn.com.nghiemduong.tradix.databinding.FragmentHomeBinding
import vn.com.nghiemduong.tradix.model.FilterTradix
import vn.com.nghiemduong.tradix.model.Tradix

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListfilterTraxdixs: MutableList<FilterTradix>
    private lateinit var mFilterTradixAdapter: FilterTradixAdapter
    private lateinit var mTradixAdapter: TradixAdapter
    private lateinit var mListTradixs: MutableList<Tradix>
    private var index = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        addListFilterTradixs()
        addListTradixs()
        setUpRecyclerFilterTradix()
        setUpRecyclerTradix()

        binding.tvLoadMoreTradix.setOnClickListener {
            addLoadMoreTradix()
        }
        return binding.root
    }

    private fun setUpRecyclerTradix() {
        binding.rcvTradix.setHasFixedSize(true)
        binding.rcvTradix.layoutManager =
            LinearLayoutManager(context)
        mTradixAdapter = TradixAdapter(mListTradixs)
        binding.rcvTradix.adapter = mTradixAdapter
    }

    private fun addListTradixs() {
        mListTradixs = mutableListOf(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45", "20.047,50",
                "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )
        index++
        mListTradixs.add(
            Tradix(
                "FTSE 100 $index", "LONDON", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )
        index++
        mListTradixs.add(
            Tradix(
                "IBEX 35 $index", "MADRID", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )
        index++
        mListTradixs.add(
            Tradix(
                "DAX $index", "XETRA", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )
    }

    private fun addLoadMoreTradix() {
        index++
        mListTradixs.add(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "FTSE 100 $index", "LONDON", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "IBEX 35 $index", "MADRID", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "DAX $index", "XETRA", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "FTSE 100 $index", "LONDON", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "IBEX 35 $index", "MADRID", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mListTradixs.add(
            Tradix(
                "DAX $index", "XETRA", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )
        mTradixAdapter.mListTradixs = mListTradixs
        mTradixAdapter.notifyDataSetChanged()
    }


    private fun setUpRecyclerFilterTradix() {
        binding.rcvFilterTradix.setHasFixedSize(true)
        binding.rcvFilterTradix.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mFilterTradixAdapter = FilterTradixAdapter(mListfilterTraxdixs)
        binding.rcvFilterTradix.adapter = mFilterTradixAdapter
    }

    private fun addListFilterTradixs() {
        mListfilterTraxdixs = mutableListOf(
            FilterTradix("INDEX"),
            FilterTradix("SHARES"),
            FilterTradix("CURRENCIES"),
            FilterTradix("FUTURES"),
            FilterTradix("cry"),
            FilterTradix("INDEX")
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}