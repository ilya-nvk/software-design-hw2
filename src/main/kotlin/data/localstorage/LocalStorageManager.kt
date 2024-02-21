package com.ilyanvk.data.localstorage

/**
 * LocalStorageManager is a generic interface for managing local storage.
 * It provides methods for writing data to a file and reading data from a file.
 *
 * @param T The type of the data object that this manager will handle.
 */
interface LocalStorageManager<T> {

    /**
     * Writes data of type T to a file.
     *
     * @param data The data of type T to be written to the file.
     */
    fun writeToFile(data: T)

    /**
     * Reads data of type T from a file.
     *
     * @return The data of type T read from the file.
     */
    fun readFromFile(): T?
}