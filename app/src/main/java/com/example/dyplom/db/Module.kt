package com.example.dyplom.db

import java.io.Serializable
// Formulaz danych
class Module (var id: Int?, val Name: String, var Clock: Double, var RAM: Int) : Comparable<Module>, Serializable {
    override fun compareTo(other: Module): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (other is Module){
            return id == other.id
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "id: $id, Name: $Name, Clock: $Clock, RAM: $RAM"
    }
}