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
import kotlinx.android.synthetic.main.search_fragment.*
import ru.strorin.skyeng.R
import ru.strorin.skyeng.data.Translation
import ru.strorin.skyeng.ui.search.translations.TranslationAdapter

class SearchFragment: Fragment(), SearchView {

    private lateinit var searchButton: Button
    private lateinit var queryInput: EditText
    private lateinit var meaningRecyclerView: RecyclerView

    private lateinit var adapter: TranslationAdapter

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
            model.onSearchClicked(queryInput.text.toString(), this)
        }
    }

    override fun setNetworkError() {
        meaningRecyclerView.visibility = View.GONE
        error_layout.visibility = View.VISIBLE
        error_text.setText(R.string.str_problem_with_connection)
        error_image.setImageResource(R.drawable.ic_baseline_wifi_48)    }

    override fun setTranslationsList(list: List<Translation>) {
        if (list.isNotEmpty()) {
            adapter.setDataset(list)
            meaningRecyclerView.visibility = View.VISIBLE
            error_layout.visibility = View.GONE
        } else {
            showEmptyTranslationsList()
        }
    }

    private fun showEmptyTranslationsList() {
        meaningRecyclerView.visibility = View.GONE
        error_layout.visibility = View.VISIBLE
        error_text.setText(R.string.str_no_translations_found)
        error_image.setImageResource(R.drawable.ic_outline_error_outline_48)
    }

    private fun setupUi(context: Context){
        meaningRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = TranslationAdapter(context)
        meaningRecyclerView.adapter = adapter
    }

    private fun findViews(view: View) {
        searchButton = view.findViewById(R.id.search_button)
        queryInput = view.findViewById(R.id.query_input)
        meaningRecyclerView = view.findViewById(R.id.translation_table)
    }
}