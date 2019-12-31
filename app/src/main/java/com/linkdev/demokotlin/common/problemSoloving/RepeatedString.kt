package com.linkdev.demokotlin.common.problemSoloving

// Complete the repeatedString function below.
fun repeatedString(s: String, n: Long): Long {
    var numberOfA: Long = 0
    var wholeString = ""
    var timesOfAppendedString: Long = 0
    var allRepeatedTimes: Long
    if (s.length > 1) {
        s.forEach { c: Char ->
            if (c.toString().equals("a"))
                numberOfA++
        }

        if (n % s.length == 0L) {
            wholeString = repeatString(s,n / s.length)
            allRepeatedTimes = (wholeString.length / s.length) * numberOfA
        } else {
            val remainder = n % s.length
            wholeString =repeatString( s,(n - remainder) / s.length)
            s.take(remainder.toInt()).forEach { c: Char ->
                if (c.toString().equals("a"))
                    timesOfAppendedString++
            }
            allRepeatedTimes = ((wholeString.length / s.length) * numberOfA) + timesOfAppendedString
        }


        return allRepeatedTimes
    } else if (s.equals("a")) {
        return n
    } else {
        return 0
    }
}

fun repeatString(string: String, numTimes: Long): String {
    var output: String = "";
    for (item: Long in 1..numTimes)
        output += string;
    return output;
}
//fun String.repeat(times: Long): String {
//    val inner = (times / Integer.MAX_VALUE).toInt()
//    val remainder = (times % Integer.MAX_VALUE).toInt()
//    return buildString {
//        repeat(inner) {
//            append(this@repeat.repeat(Integer.MAX_VALUE))
//        }
//        append(this@repeat.repeat(remainder))
//    }
//}

fun main(args: Array<String>) {
    repeatedString("cfimaakj", 10)
}