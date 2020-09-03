package ru.strorin.skyeng.ui.search.translations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.strorin.skyeng.R
import ru.strorin.skyeng.data.Word

internal class TranslationAdapter(
    context: Context,
    private val clickListener: OnItemClickListener
): RecyclerView.Adapter<TranslationViewHolder>() {

    private var translationList = ArrayList<Word>()
    private val inflater = LayoutInflater.from(context)

    private val positionClickListener = object: OnPositionClickListener {
        override fun onItemClick(position: Int) {
            clickListener.onItemClick(translationList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        return TranslationViewHolder(
            inflater.inflate(R.layout.translation_list_item, parent, false),
            positionClickListener
        )
    }

    override fun getItemCount(): Int = translationList.size

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(translationList[position])
    }

    fun setDataset(words: List<Word>) {
        translationList.clear()
        translationList.addAll(words)
        notifyDataSetChanged()
    }

    internal interface OnPositionClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemClickListener {
        fun onItemClick(word: Word)
    }
}