package com.example.dyplom.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.*

class DataManager(context: Context) {

    private val db: SQLiteDatabase = context.openOrCreateDatabase("Assessment", Context.MODE_PRIVATE, null)

    init {
        // Polecenia SQL
        // W tym miejścu mamy stworzyć tabele w której będzie zapisana baza danych
        val programCreateQuery =
            "CREATE TABLE IF NOT EXISTS `Programs` ( `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Name` TEXT NOT NULL, `Clock` DOUBLE NOT NULL, 'RAM'  INTEGER NOT NULL)"
        /*val statisticsCreateQuery =
            "CREATE TABLE IF NOT EXISTS `Statistic` ( `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Title` TEXT NOT NULL,`nr_1` DOUBLE,`nr_2` DOUBLE,`nr_3` DOUBLE,`nr_4` DOUBLE,`nr_5` DOUBLE,`nr_6` DOUBLE,`nr_7` DOUBLE,`nr_8` DOUBLE,`nr_9` DOUBLE,`nr_10` DOUBLE,`nr_11` DOUBLE, `nr_12` DOUBLE)"
        */
        //Wykonanie stworzenego polecenie

        db.execSQL(programCreateQuery)
        //db.execSQL(statisticsCreateQuery)
    }

    fun add(module: Module): Boolean {
        // Ta metoda wykonuje dodanie wartości do bazy danych przy warunku że nie istnieje rekord o tej samej nazwie firmy
        return if (module(module.Name) == null) {
            val query = "INSERT INTO Programs (Name, Clock, RAM) VALUES ('${module.Name}', ${module.Clock}, ${module.RAM})"
            db.execSQL(query)
            true
        } else {
            false
        }
    }

    /*fun addStatics(dataStatics: DataStatics): Boolean {

        return if (module(dataStatics.Title) == null) {
            val query = "INSERT INTO Statistic (Title, nr_1, nr_2, nr_3, nr_4, nr_5, nr_6, nr_7, nr_8, nr_9, nr_10, nr_11, nr_12) VALUES ('${dataStatics.Title}', ${dataStatics.nr_1}, ${dataStatics.nr_2},${dataStatics.nr_3},${dataStatics.nr_4},${dataStatics.nr_5},${dataStatics.nr_6},${dataStatics.nr_7},${dataStatics.nr_8},${dataStatics.nr_9},${dataStatics.nr_10},${dataStatics.nr_11}, ${dataStatics.nr_12})"
            db.execSQL(query)
            true
        } else {
            false
        }
    }*/
    fun allModules(): List<Module> {
        // Ta metoda wykonuje pobranie wszystkich rekordów
        // Tworzenie listy dla zapisywania rekordów
        val modules = mutableListOf<Module>()
        // Otworzenie cursora
        val cursor = db.rawQuery("SELECT * FROM Programs", null)
        // Przesuniecie kursora do pierwszego wiersza
        if (cursor.moveToFirst()) {
            // Pętła która przechodzi do następnego wierza po przeczytaniu danego
            do {
                val clock = cursor.getDouble(cursor.getColumnIndex("Clock"))
                val RAM = cursor.getInt(cursor.getColumnIndex("RAM"))
                val name = cursor.getString(cursor.getColumnIndex("Name"))
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                // Tworzenie formularza danych
                val module = Module(id, name, clock, RAM)
                // Zapis formularza do listy
                modules.add(module)
            } while (cursor.moveToNext())
        }
        // Zamknięcie kursora
        cursor.close()
        return modules.sorted()
    }
    fun reqModules(clockP: Double, ram: Int): List<Module> {

        // Ta metoda wykonuje pobieranie rekordów o pewnych wartościach taktowania a Ram
        // Tworzenie listy dla zapisywania rekordów
        val modules = mutableListOf<Module>()
        // Otworzenie cursora
        val cursor = db.rawQuery("SELECT * FROM Programs WHERE Clock>='$clockP' AND RAM>='$ram'", null)
        // Przesuniecie kursora do pierwszego wiersza
        if (cursor.moveToFirst()) {
            // Pętła która przechodzi do następnego wierza po przeczytaniu danego
            do {
                val clock = cursor.getDouble(cursor.getColumnIndex("Clock"))
                val RAM = cursor.getInt(cursor.getColumnIndex("RAM"))
                val name = cursor.getString(cursor.getColumnIndex("Name"))
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                // Tworzenie formularza danych
                val module = Module(id, name, clock, RAM)
                // Zapis formularza do listy
                modules.add(module)
            } while (cursor.moveToNext())
        }
        // Zamknięcie kursora
        cursor.close()
        return modules.sorted()
    }
    fun module(code: String): Module? {
        // Ta metoda wykonuje pobieranie  rekordów o pewnych wartościach nazwy programu
        // Tworzenie listy dla zapisywania rekordów
        val query = "SELECT * FROM Programs WHERE Name='$code'"
        // Otworzenie cursora
        val cursor = db.rawQuery(query, null)
        // Przesuniecie kursora do pierwszego wiersza
        return if (cursor.moveToFirst()) {
            // Pętła która przechodzi do następnego wierza po przeczytaniu danego
            val clock = cursor.getDouble(cursor.getColumnIndex("Clock"))
            val RAM = cursor.getInt(cursor.getColumnIndex("RAM"))
            val name = cursor.getString(cursor.getColumnIndex("Name"))
            val id = cursor.getInt(cursor.getColumnIndex("Id"))
            // Zamknięcie kursora
            cursor.close()
            // Tworzenie formularza danych oraz wysyłanie danych
            Module(id, name, clock, RAM)
        } else {
            cursor.close()
            null
        }
    }

   /* fun allStatistics(): List<DataStatics> {

        val modules = mutableListOf<DataStatics>()

        val cursor = db.rawQuery("SELECT * FROM Statistic", null)
        if (cursor.moveToFirst()) {
            do {
                val nr_1 = cursor.getDouble(cursor.getColumnIndex("nr_1"))
                val nr_2 = cursor.getDouble(cursor.getColumnIndex("nr_2"))
                val nr_3 = cursor.getDouble(cursor.getColumnIndex("nr_3"))
                val nr_4 = cursor.getDouble(cursor.getColumnIndex("nr_4"))
                val nr_5 = cursor.getDouble(cursor.getColumnIndex("nr_5"))
                val nr_6 = cursor.getDouble(cursor.getColumnIndex("nr_6"))
                val nr_7 = cursor.getDouble(cursor.getColumnIndex("nr_7"))
                val nr_8 = cursor.getDouble(cursor.getColumnIndex("nr_8"))
                val nr_9 = cursor.getDouble(cursor.getColumnIndex("nr_9"))
                val nr_10 = cursor.getDouble(cursor.getColumnIndex("nr_10"))
                val nr_11 = cursor.getDouble(cursor.getColumnIndex("nr_11"))
                val nr_12 = cursor.getDouble(cursor.getColumnIndex("nr_12"))
                val name = cursor.getString(cursor.getColumnIndex("Title"))
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val module = DataStatics(id, name, nr_1, nr_2, nr_3, nr_4, nr_5, nr_6, nr_7, nr_8, nr_9, nr_10, nr_11, nr_12)
                modules.add(module)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return modules.sorted()
    }*/

}