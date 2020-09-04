package ru.strorin.skyeng.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.strorin.skyeng.R
import ru.strorin.skyeng.network.WordTranslationDao
import java.util.regex.Pattern

class DetailsFragment: Fragment(), DetailsView {

    private var word: WordTranslationDao? = null
    private lateinit var wordView: TextView
    private lateinit var meaningView: TextView
    private lateinit var imageView: ImageView
    private lateinit var playButton: ImageView

    val args: DetailsFragmentArgs by navArgs()

    companion object {
        fun newInstance(word: WordTranslationDao): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.word = word
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        findViews(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        word = args.word
        fillWordData()
    }

    private fun fillWordData() {
        val w = word ?: return
        wordView.text = w.text
        meaningView.text = w.meanings[0].translation.text
        if (w.meanings.isNotEmpty()) {
            var url = w.meanings[0].imageUrl
            if (url.startsWith("//")) {
                url = "https:$url"
            }
            Glide.with(imageView.context).load(url).into(imageView);
        }
    }

    private fun findViews(view: View) {
        wordView = view.findViewById(R.id.textWord)
        meaningView = view.findViewById(R.id.meaningView)
        imageView = view.findViewById(R.id.imageView)
        playButton = view.findViewById(R.id.playButton)
    }
}