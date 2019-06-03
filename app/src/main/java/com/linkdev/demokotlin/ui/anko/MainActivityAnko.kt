package com.linkdev.demokotlin.ui.anko

import android.os.Bundle
import com.linkdev.demokotlin.ui.base.BaseActivityForAnko
import org.jetbrains.anko.*

class MainActivityAnko : BaseActivityForAnko() {
    override fun setListeners() {

    }

    override fun initializeViews() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    fun tryLogin(ui: AnkoContext<MainActivityAnko>, name: CharSequence?, password: CharSequence?) {
        ui.doAsync {

            activityUiThreadWithContext {
                if (checkCredentials(name.toString(), password.toString())) {
                    showAlert()

                } else {
                    toast("Wrong password :( Enter user:password")
                }
            }
        }
    }

    private fun checkCredentials(name: String, password: String) = name == "user" && password == "123"

    private fun showAlert() {
        alert("Hi, I'm ....", "Have you tried turning it off and on again?") {
            yesButton { toast("Logged in! :)") }
            noButton {}
        }.show()
    }
}

