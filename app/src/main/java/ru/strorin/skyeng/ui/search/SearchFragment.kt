package ru.strorin.skyeng.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.strorin.skyeng.R
import ru.strorin.skyeng.data.Translation

class SearchFragment: Fragment(), SearchView {

    private lateinit var searchButton: Button
    private lateinit var queryInput: EditText
    private lateinit var meaningRecyclerView: RecyclerView

    private val model: SearchViewModel by viewModels { SearchViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        findViews(view)
        setupUi(inflater.context)
        return view
    }

    override fun onStart() {
        super.onStart()
        searchButton.setOnClickListener {
            model.onSearchClicked(queryInput.text.toString())
        }
    }

    override fun setQueryError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNetworkError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTranslationsList(list: List<Translation>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupUi(context: Context){
        meaningRecyclerView.layoutManager = LinearLayoutManager(context)

//        meaningRecyclerView = BidRecyclerAdapter(context)
//        bidsRecyclerView.adapter = bidAdapter
    }

    private fun findViews(view: View) {
        searchButton = view.findViewById(R.id.search_button)
        queryInput = view.findViewById(R.id.query_input)
        meaningRecyclerView = view.findViewById(R.id.translation_table)
    }
}