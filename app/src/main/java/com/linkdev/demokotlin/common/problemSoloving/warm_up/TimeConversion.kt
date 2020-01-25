package com.linkdev.demokotlin.common.problemSoloving.warm_up

/*
 * Complete the timeConversion function below.
 */
fun timeConversion(s: String): String {
    /*
     * Write your code here.

     */
    val hours = s.take(2).toInt()

    return if (s.contains("AM") && hours == 12) {
        "00" + s.slice(2..7)
    } else if (s.contains("AM")) {
        s.slice(0..7)
    } else {

       val time = if (hours != 12)
            (hours + 12).toString() + s.slice(2..7)
        else
            (hours).toString() + s.slice(2..7)

        if (time.length == 8) {
            time
        } else {
            "0$time"
        }
    }
}

fun main(args: Array<String>) {
    timeConversion("12:45:54PM")


}