package com.example.lab2luisabrego_elizabethabrego

data class Persona(
    val nombre: String,
    val edad: Int,
    val departamento: String
) {
    // Sobrescribimos toString para que muestre solo el nombre en el ListView
    override fun toString(): String {
        return nombre
    }
}