package com.ilyanvk.domain.model

data class MenuItem(val name: String, val price: Double, val preparationTimeMin: Int) {
    override fun toString(): String {
        return "$name - $price rubles - $preparationTimeMin min"
    }
}