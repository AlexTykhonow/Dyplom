package com.example.dyplom.db

import java.io.Serializable

class DataStatics(var id: Int?, val Title: String, var nr_1: Double, var nr_2: Double, var nr_3: Double, var nr_4: Double, var nr_5: Double, var nr_6: Double, var nr_7: Double, var nr_8: Double, var nr_9: Double, var nr_10: Double, var nr_11: Double, var nr_12: Double) : Comparable<DataStatics>, Serializable  {
    override fun compareTo(other: DataStatics): Int {
        return Title.compareTo(other.Title)
    }
}