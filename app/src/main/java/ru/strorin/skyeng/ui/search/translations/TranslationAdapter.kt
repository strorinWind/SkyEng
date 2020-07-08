package ru.strorin.skyeng.ui.search.translations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.strorin.skyeng.R
import ru.strorin.skyeng.data.Translation

class TranslationAdapter(
    context: Context
): RecyclerView.Adapter<TranslationViewHolder>() {

    private var translationList = ArrayList<Translation>()
    private val inflater = LayoutInflater.from(context);

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        return TranslationViewHolder(
            inflater.inflate(R.layout.translation_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = translationList.size

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(translationList[position])
    }

    fun setDataset(translations: List<Translation>) {
        translationList.clear()
        translationList.addAll(translations)
        notifyDataSetChanged()
    }
}