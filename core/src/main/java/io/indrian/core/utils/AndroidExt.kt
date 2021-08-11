package io.indrian.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

fun Date.displayDate(): String {
    return try {
        SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid Date"
    }
}

fun String.toDate(): Date {
    return try {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this) ?: Date()
    } catch (e: Exception) {
        e.printStackTrace()
        Date()
    }
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun DialogFragment.delayJob(
    durationInMillis: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
) = CoroutineScope(dispatcher).launch {
    delay(durationInMillis)
    block()
}

fun ShimmerFrameLayout.show() {
    startShimmer()
    visibility = View.VISIBLE
}

fun ShimmerFrameLayout.hide() {
    stopShimmer()
    visibility = View.INVISIBLE
}