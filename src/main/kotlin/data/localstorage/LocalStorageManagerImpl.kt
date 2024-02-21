package com.ilyanvk.data.localstorage

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

class LocalStorageManagerImpl<T>(
    private val fileName: String,
    private val typeToken: TypeToken<T>,
) : LocalStorageManager<T> {
    private val gson = Gson()

    override fun writeToFile(data: T) {
        try {
            val jsonString = gson.toJson(data)
            File(RESOURCES_PATH + fileName).writeText(jsonString)
        } catch (e: IOException) {
            println("Error writing to file: ${e.message}")
        }
    }

    override fun readFromFile(): T? {
        return try {
            val jsonString = File(RESOURCES_PATH + fileName).readText()
            gson.fromJson(jsonString, typeToken.type)
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val RESOURCES_PATH = "src/main/resources/"
    }
}