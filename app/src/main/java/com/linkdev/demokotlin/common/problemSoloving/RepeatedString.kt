package com.linkdev.demokotlin.common.problemSoloving

// Complete the repeatedString function below.
fun repeatedString(s: String, n: Long): Long {
    var numberOfA: Long = 0
    var timesOfAppendedString: Long = 0
    var allRepeatedTimes: Long
    if (s.length > 1) {
        s.forEach { c: Char ->
            if (c.toString().equals("a"))
                numberOfA++
        }

        if (n % s.length == 0L) {
            allRepeatedTimes = (n / s.length) * numberOfA
        } else {
            val remainder = n % s.length
            s.take(remainder.toInt()).forEach { c: Char ->
                if (c.toString().equals("a"))
                    timesOfAppendedString++
            }
            allRepeatedTimes = ((n / s.length) * numberOfA) + timesOfAppendedString
        }


        return allRepeatedTimes
    } else if (s.equals("a")) {
        return n
    } else {
        return 0
    }
}

fun main(args: Array<String>) {
    repeatedString("aba", 10)
}