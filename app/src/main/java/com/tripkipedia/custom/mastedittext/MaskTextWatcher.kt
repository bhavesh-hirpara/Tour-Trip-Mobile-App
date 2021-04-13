package com.santalu.maskedittext

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */

class MaskTextWatcher(
  private val editText: TextInputEditText,
  private val mask: String
) : TextWatcher {

  private var selfChange = false

  override fun afterTextChanged(s: Editable?) {
    if (selfChange) return
    selfChange = true
    format(s)
    selfChange = false
  }

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
  }

  fun format(text: Editable?) {// find next letter or digit

    // set correct cursor position when editing

    // restore input filters
    // reset input filters

    // apply mask
    when {
      text.isNullOrEmpty() -> return
      else -> text.apply {
        // reset input filters
        val editableFilters = filters
        filters = emptyArray()

        val formatted = StringBuilder()
        val list = toMutableList()

        // apply mask
        mask.forEach { m ->
          // find next letter or digit
          when {
            list.isNullOrEmpty() -> return@forEach
            else -> {
              var c = list[0]
              when {
                  m.isPlaceHolder() -> {
                    when {
                        !c.isLetterOrDigit() -> {
                          // find next letter or digit
                          val iterator = list.iterator()
                          while (iterator.hasNext()) {
                            c = iterator.next()
                            if (c.isLetterOrDigit()) break
                            iterator.remove()
                          }
                        }
                    }
                      if (list.isNullOrEmpty()) return@forEach
                      formatted.append(c)
                      list.removeAt(0)
                  }
                  else -> {
                      formatted.append(m)
                      if (m == c) {
                        list.removeAt(0)
                      }
                  }
              }
            }
          }
        }
        val previousLength = length
        val currentLength = formatted.length
        replace(0,previousLength,formatted,0,currentLength)

        // set correct cursor position when editing
        when {
          currentLength < previousLength -> {
            val currentSelection = findCursorPosition(text,editText.selectionStart)
            editText.setSelection(currentSelection)
          }


// restore input filters
        }

        // restore input filters
        filters = editableFilters
      }
    }
  }

  fun unformat(text: Editable?): String? {
    if (text.isNullOrEmpty()) return null
    val unformatted = StringBuilder()
    val textLength = text.length
    mask.forEachIndexed { index, m ->
      if (index >= textLength) return@forEachIndexed
      if (m.isPlaceHolder()) {
        unformatted.append(text[index])
      }
    }
    return unformatted.toString()
  }

  private fun findCursorPosition(text: Editable?, start: Int): Int {
    if (text.isNullOrEmpty()) return start
    val textLength = text.length
    val maskLength = mask.length
    var position = start
    for (i in start until maskLength) {
      if (mask[i].isPlaceHolder()) {
        break
      }
      position++
    }
    position++
    return if (position < textLength) position else textLength
  }
}