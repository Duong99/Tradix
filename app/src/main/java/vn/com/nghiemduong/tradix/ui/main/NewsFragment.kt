package vn.com.nghiemduong.tradix.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.adapter.FilterNewsAdapter
import vn.com.nghiemduong.tradix.adapter.NewsAdapter
import vn.com.nghiemduong.tradix.databinding.FragmentNewsBinding
import vn.com.nghiemduong.tradix.model.FilterTitle
import vn.com.nghiemduong.tradix.model.News

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListFilterNews: MutableList<FilterTitle>
    private lateinit var mFilterNewsAdapter: FilterNewsAdapter

    private lateinit var mListNews: MutableList<News>
    private lateinit var mNewsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        addListFilterNews()
        setUpRcvFilterNews()

        addListNews()
        setUpRcvNews()
        return binding.root
    }

    private fun setUpRcvNews() {
        binding.rcvNews.setHasFixedSize(true)
        binding.rcvNews.layoutManager = LinearLayoutManager(context)
        mNewsAdapter = NewsAdapter(mListNews)
        binding.rcvNews.adapter = mNewsAdapter
    }

    private fun addListNews() {
        mListNews =
            mutableListOf(
                News(
                    R.drawable.atlantia,
                    "ALT -3,87%",
                    "ATLANTIA",
                    "Illum velit nam voluptatum enim aut ratione ratione officiis totam." +
                            "Mollitia eum sint tempora ducimus",
                    "3 Sept 2020"
                ),
                News(
                    R.drawable.xiaomi,
                    "HKD -2,13%",
                    "ATLANTIA",
                    "Illum velit nam voluptatum enim aut ratione ratione officiis totam." +
                            "Mollitia eum sint tempora ducimus",
                    "2 Sept 2020"
                ),
                News(
                    R.drawable.apple,
                    "AAPL -0,91%",
                    "ATLANTIA",
                    "Illum velit nam voluptatum enim aut ratione ratione officiis totam." +
                            "Mollitia eum sint tempora ducimus",
                    "1 Sept 2020"
                )
            )
    }

    private fun setUpRcvFilterNews() {
        binding.rcvFilterNews.setHasFixedSize(true)
        binding.rcvFilterNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mFilterNewsAdapter = FilterNewsAdapter(mListFilterNews)
        binding.rcvFilterNews.adapter = mFilterNewsAdapter
    }

    private fun addListFilterNews() {
        mListFilterNews = mutableListOf(
            FilterTitle("EDITORIAL"),
            FilterTitle("CRYPTO NEWS"),
            FilterTitle("RAW MATERIAL"),
            FilterTitle("ECONOMICS"),
            FilterTitle("EDITORIAL"),
            FilterTitle("CRYPTO NEWS"),
            FilterTitle("RAW MATERIAL"),
            FilterTitle("ECONOMICS")
        )
    }
}