package com.linkdev.demokotlin.ui.anko

import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import com.linkdev.demokotlin.R
import org.jetbrains.anko.*


class MainActivityUI : AnkoComponent<MainActivityAnko> {
    private val customStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 26f
            is EditText -> v.textSize = 24f
        }
    }

    override fun createView(ui: AnkoContext<MainActivityAnko>) = with(ui) {
        verticalLayout {
            padding = dip(32)

            imageView(android.R.drawable.ic_menu_manage).lparams {
                margin = dip(16)
                gravity = Gravity.TOP or Gravity.CENTER

            }

            val name = editText {
                hintResource = R.string.name
            }
            val password = editText {
                hintResource = R.string.password
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }.lparams(width = matchParent, height = wrapContent)

            button("Log in") {
                //                onClick {
//                    ui.owner.tryLogin(ui, name.text, password.text)
//                }
            }


        }.applyRecursively(customStyle)
    }
}