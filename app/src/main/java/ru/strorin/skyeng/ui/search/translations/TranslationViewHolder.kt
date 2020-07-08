package ru.strorin.skyeng.ui.search.translations

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.strorin.skyeng.R
import ru.strorin.skyeng.data.Translation

class TranslationViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {

    private val textView = view.findViewById<TextView>(R.id.textView)

    fun bind(translation: Translation) {
        textView.text = translation.text
    }
}