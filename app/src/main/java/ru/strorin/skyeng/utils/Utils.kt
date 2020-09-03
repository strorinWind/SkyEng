package ru.strorin.skyeng.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService


fun hideKeyboard(context: Context) {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
//    var view: View? = activity.currentFocus
//    //If no view currently has focus, create a new one, just so we can grab a window token from it
//    if (view == null) {
//        view = View(activity)
//    }
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}