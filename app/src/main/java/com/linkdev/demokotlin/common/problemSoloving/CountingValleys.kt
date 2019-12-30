package com.linkdev.demokotlin.common.problemSoloving

import android.util.Log

fun countingValleys(n: Int, s: String) {
    var countingValleys = 0
    var difCounter = 0
    with(s) {
        forEachIndexed { _, char ->
            if (char.toString().toLowerCase() == "u".toLowerCase()) {
                difCounter++
            } else {
                difCounter--
            }
            if (difCounter >= 0) {
                countingValleys++
            } else if (difCounter == 0) {
                countingValleys = 0
            }
        }
    }
    Log.d("countingValleys", "countingValleys>>>$countingValleys")

}

fun main(args: Array<String>) {


    countingValleys(8, "DDUUDDUDUUUD")


}