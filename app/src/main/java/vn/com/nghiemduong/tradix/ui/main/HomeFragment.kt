package vn.com.nghiemduong.tradix.ui.main

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.adapter.FilterTradixAdapter
import vn.com.nghiemduong.tradix.adapter.TradixAdapter
import vn.com.nghiemduong.tradix.databinding.FragmentHomeBinding
import vn.com.nghiemduong.tradix.model.FilterTitle
import vn.com.nghiemduong.tradix.model.Tradix

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListfilterTraxdixs: MutableList<FilterTitle>
    private lateinit var mFilterTradixAdapter: FilterTradixAdapter
    private lateinit var mTradixAdapter: TradixAdapter
    private lateinit var layoutFilterTradix: LayoutManager
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

        val mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder,
                    target: ViewHolder
                ): Boolean {
                    mTradixAdapter.notifyItemMoved(viewHolder.layoutPosition, target.layoutPosition)
                    Log.d("TAG", "onMove: ${viewHolder.layoutPosition} - ${target.layoutPosition}")
                    return true
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    val dialog = context?.let { AlertDialog.Builder(it) }

                    dialog?.setMessage(
                        "Do you want delete " +
                                "${mTradixAdapter.mListTradixs[viewHolder.adapterPosition].title}?"
                    )

                    dialog?.setTitle("Warring")
                    dialog?.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                        mTradixAdapter.deleteTradix(viewHolder.adapterPosition)
                        binding.rcvTradix.swapAdapter(mTradixAdapter, true)
                        Toast.makeText(context, "Remove item successfully", Toast.LENGTH_SHORT)
                            .show()
                    })

                    dialog?.setNegativeButton("No") { _, _ ->
                        mTradixAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }

                    dialog?.show()
                }
            })

        mIth.attachToRecyclerView(binding.rcvTradix)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpRecyclerTradix() {
        binding.rcvTradix.setHasFixedSize(true)
        binding.rcvTradix.layoutManager =
            LinearLayoutManager(context)
        binding.rcvTradix.adapter = mTradixAdapter
    }

    private fun addListTradixs() {
        val mListTradixs: MutableList<Tradix> = mutableListOf(
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

        mTradixAdapter = TradixAdapter()
        mTradixAdapter.mListTradixs = mListTradixs
        mTradixAdapter.notifyDataSetChanged()
    }

    private fun addLoadMoreTradix() {
        index++
        mTradixAdapter.addTradix(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "FTSE 100 $index", "LONDON", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "IBEX 35 $index", "MADRID", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "DAX $index", "XETRA", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "FTSE 100 $index", "LONDON", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "IBEX 35 $index", "MADRID", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "DAX $index", "XETRA", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "DOWN JONES $index", "NYSE", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        index++
        mTradixAdapter.addTradix(
            Tradix(
                "FTSE 100 $index", "LONDON", "10:44:45",
                "20.047,50", "+203 (+1,04%)"
            )
        )

        mTradixAdapter.notifyDataSetChanged()
    }


    private fun setUpRecyclerFilterTradix() {
        binding.rcvFilterTradix.setHasFixedSize(true)
        layoutFilterTradix = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvFilterTradix.layoutManager = layoutFilterTradix
        mFilterTradixAdapter = FilterTradixAdapter(mListfilterTraxdixs)
        binding.rcvFilterTradix.adapter = mFilterTradixAdapter
    }

    private fun addListFilterTradixs() {
        mListfilterTraxdixs = mutableListOf(
            FilterTitle("INDEX"),
            FilterTitle("SHARES"),
            FilterTitle("CURRENCIES"),
            FilterTitle("FUTURES"),
            FilterTitle("cry"),
            FilterTitle("INDEX")
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}