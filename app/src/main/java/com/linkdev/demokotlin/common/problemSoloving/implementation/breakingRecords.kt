package com.linkdev.demokotlin.common.problemSoloving.implementation

// Complete the breakingRecords function below.
fun breakingRecords(scores: Array<Int>): Array<Int> {
    var hightestScore = scores[0]
    var lowestScore = scores[0]

    var numberOfPositiveRecord = 0
    var numberOfNagativeRecord = 0

    scores.forEachIndexed { _, value ->
        if (hightestScore < value) {
            hightestScore = value
            numberOfPositiveRecord++
        }
        if (lowestScore > value) {
            lowestScore = value
            numberOfNagativeRecord++

        }

    }
    return arrayOf(numberOfPositiveRecord, numberOfNagativeRecord)
}

fun main(args: Array<String>) {
    breakingRecords(
        arrayOf(
            3, 4, 21, 36, 10, 28, 35, 5, 24, 42
        )
    )


}