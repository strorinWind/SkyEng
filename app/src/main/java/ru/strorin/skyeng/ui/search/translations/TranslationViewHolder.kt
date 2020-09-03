package ru.strorin.skyeng.ui.search.translations

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.strorin.skyeng.R
import ru.strorin.skyeng.data.Word

internal class TranslationViewHolder(
    view: View,
    listener: TranslationAdapter.OnPositionClickListener
): RecyclerView.ViewHolder(view) {

    private val textView = view.findViewById<TextView>(R.id.textView)
    private val meaningView = view.findViewById<TextView>(R.id.meaningView)

    init {
        view.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun bind(word: Word) {
        textView.text = word.text
        meaningView.text = word.meanings
    }
}