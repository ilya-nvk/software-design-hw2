package com.ilyanvk.presentation

class InputReaderImpl : InputReader {
    override fun readChoice(prompt: String): Int {
        print(prompt)
        return try {
            readln().toInt()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            -1
        }
    }

    override fun readString(prompt: String): String {
        print(prompt)
        return readln()
    }
}