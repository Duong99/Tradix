package vn.com.nghiemduong.tradix.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import vn.com.nghiemduong.tradix.adapter.FilterEditorNewsAdapter
import vn.com.nghiemduong.tradix.databinding.FragmentNewsArticleBinding
import vn.com.nghiemduong.tradix.model.FilterTitle
import vn.com.nghiemduong.tradix.model.News
import vn.com.nghiemduong.tradix.ui.MainActivity
import vn.com.nghiemduong.tradix.viewmodel.NewsViewModel

class NewsArticleFragment : Fragment() {
    private var _binding: FragmentNewsArticleBinding? = null
    private val binding get() = _binding!!

    private var news: News? = null
    private lateinit var filterEditorNews: FilterEditorNewsAdapter
    private lateinit var mListFilterEditorNews: ArrayList<FilterTitle>
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsArticleBinding.inflate(inflater, container, false)

        /*news = mainActivity.newsViewModel.news
        binding.news = news*/
        getDataNews()

        setUpRcvFilterEditorNews()

        binding.ivBackNewsDetail.setOnClickListener {
            mainActivity.onBackPressed()
        }

        binding.tvContentNewsArticle.text =
            "Rem deserunt voluptatem sapiente. Quod sint officiis " +
                    "quae magnam. Qui eaque atque quia. Incidunt dolor" +
                    "reiciendis tenetur libero error consequatur voluptate" +
                    "recusandae. Sequi voluptatum quas. Ullam voluptatem" +
                    "reprehenderit ea commodi. Doloremque autem" +
                    "praesentium qui harum quia sunt voluptatem eius at." +
                    "Voluptates voluptatem eaque et voluptas maxime" +
                    "molestiae et. Et saepe perferendis ut quidem et est. Et" +
                    "iusto ut nostrum delectus. Libero et modi placeat" +
                    "labore sit et quaerat sed. Dolorem libero earum ipsum" +
                    "illum nemo." +
                    "\n\n" +
                    "Rem deserunt voluptatem sapiente. Quod sint officiis" +
                    "quae magnam. Qui eaque atque quia. Incidunt dolor" +
                    "molestiae et. Et saepe perferendis ut quidem et est. Et" +
                    "iusto ut nostrum delectus. Libero et modi placeat" +
                    "labore sit et quaerat sed. Dolorem libero earum ipsum" +
                    "illum nemo."
        return binding.root
    }

    private fun setUpRcvFilterEditorNews() {
        binding.rcvFilterEditorNews.setHasFixedSize(true)
        binding.rcvFilterEditorNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        filterEditorNews = FilterEditorNewsAdapter(mListFilterEditorNews)
        binding.rcvFilterEditorNews.adapter = filterEditorNews
    }

    private fun getDataNews() {
        news = this.arguments?.getSerializable("NEWS") as News
        mListFilterEditorNews =
            this.arguments?.getParcelableArrayList<FilterTitle>("LIST_RCV") as ArrayList<FilterTitle>
        binding.news = news
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}