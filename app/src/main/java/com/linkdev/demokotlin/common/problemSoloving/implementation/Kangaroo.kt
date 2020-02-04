package com.linkdev.demokotlin.common.problemSoloving.implementation

// Complete the kangaroo function below.
fun kangaroo(x1: Int, v1: Int, x2: Int, v2: Int): String {
    return if((x2 > x1 && v2 >= v1) || ((x1-x2) % (v2-v1)) != 0)
        "NO"
    else
        "YES"

}

fun main(args: Array<String>) {
    kangaroo(0, 2, 5, 3)


}