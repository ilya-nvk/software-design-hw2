package com.ilyanvk.data.dao

/**
 * GenericDao is a generic interface for data access objects (DAOs).
 * It provides common methods for handling data of type T.
 *
 * @param T The type of the data object that this DAO will handle.
 */
interface GenericDao<T> {

    /**
     * Retrieves all items of type T.
     *
     * @return A list of items of type T.
     */
    fun getAll(): List<T>

    /**
     * Adds a new item of type T.
     *
     * @param item The item of type T to be added.
     */
    fun add(item: T)

    /**
     * Deletes an item of type T.
     *
     * @param item The item of type T to be deleted.
     */
    fun delete(item: T)
}