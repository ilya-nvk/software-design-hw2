package com.ilyanvk.presentation

interface InputReader {
    fun readChoice(prompt: String = ""): Int
    fun readString(prompt: String = ""): String
}