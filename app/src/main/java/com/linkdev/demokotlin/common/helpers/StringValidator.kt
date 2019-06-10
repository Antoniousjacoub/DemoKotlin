package com.linkdev.demokotlin.common.helpers

object StringValidator {
    fun validString(string: String?): String {
        return string ?: ""

    }

}
