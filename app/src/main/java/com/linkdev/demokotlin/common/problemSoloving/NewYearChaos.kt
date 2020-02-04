package com.linkdev.demokotlin.common.problemSoloving

// Complete the minimumBribes function below.
fun minimumBribes(q: Array<Int>): Unit {
    var currentValue = 0
    var numberOfBribes = 0
    var currentIteration = 0
    var j = 0
//    while (q.size > j + 1) {
//        if (q[j] > (j + 3)) {
//            println("Too chaotic")
//            return
//        }
//        if (q[j] - q[j + 1] > 0) {
//
//            if (abs(q[j] - q[j + 1]) == 1)
//                numberOfBribes++
//
//            numberOfBribes += q[j] - q[j + 1]
//        }
//
//        j++
//    }

    q.forEachIndexed { index: Int, value: Int ->
        currentIteration = index + 1
        currentValue = q[index]

        if (currentValue - currentIteration == 2) {
            numberOfBribes += 2
        } else if ((currentValue - currentIteration) == 1) {
            numberOfBribes++
        } else {

        }
    }


    println(numberOfBribes)
}

fun main(args: Array<String>) {
    val array: Array<Int> = arrayOf(
        1, 2, 5, 3, 7, 8, 6, 4

    )
    minimumBribes(array)

}