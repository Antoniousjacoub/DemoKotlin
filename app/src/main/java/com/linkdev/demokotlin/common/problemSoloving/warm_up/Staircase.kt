package com.linkdev.demokotlin.common.problemSoloving.warm_up

// Complete the staircase function below.
fun staircase(n: Int): Unit {


    for (x in 1..n){
        println((" ".repeat((n-x)) + "#".repeat(x)))
    }
}

fun main(args: Array<String>) {

    staircase(6)
}