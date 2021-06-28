package com.example.dyplom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dyplom.db.DataManager
import com.example.dyplom.db.DataStatics
import com.example.dyplom.db.Module
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class MainActivity : AppCompatActivity() {
    // Inicjalizacja bazy danych
    private lateinit var dataManager: DataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Wybór ekranu
        setContentView(R.layout.activity_main)
        // Otwieranie bazy danych
        dataManager = DataManager(this)
        var fManager=supportFragmentManager
        // JSON uzywany dla bazy danych
        var json = """
{"id":"1","Name":"SAP Supply Chain","Clock":"2.80","RAM":"1"}|,
{"id":"2","Name":"Oracle SCM Cloud","Clock":"2.80","RAM":"1"}|,
{"id":"3","Name":"Epicor SCM","Clock":"2.80","RAM":"1"}|,
{"id":"4","Name":"Infor Nexus","Clock":"2.80","RAM":"1"}|,
{"id":"5","Name":"Infor Supply Chain Management","Clock":"2.80","RAM":"1"}|,
{"id":"6","Name":"Sage Business Cloud X3","Clock":"2.80","RAM":"1"}|,
{"id":"7","Name":"Manhattan Supply Chain","Clock":"2.80","RAM":"1"}|,
{"id":"8","Name":"Blue Yonder","Clock":"2.80","RAM":"1"}|,
{"id":"9","Name":"Unison Planning","Clock":"2.80","RAM":"1"}|,
{"id":"10","Name":"Elementum","Clock":"2.80","RAM":"1"}|,
{"id":"11","Name":"MasterCam","Clock":"2.4","RAM":"8"}|,
{"id":"12","Name":"SolidWorks","Clock":"3.3","RAM":"16"}|,
{"id":"13","Name":"AutoCAD","Clock":"2.5","RAM":"16"}|,
{"id":"14","Name":"hypermill","Clock":"2.2","RAM":"16"}|,
{"id":"15","Name":"catia","Clock":"2.6","RAM":"4"}|,
{"id":"16","Name":"PowerMill","Clock":"2.6","RAM":"8"}|,
{"id":"17","Name":"Rhinoceros 3D","Clock":"2.6","RAM":"8"}|,
{"id":"18","Name":"FreeCAD","Clock":"2.80","RAM":"8"}|,
{"id":"19","Name":"linuxcnc","Clock":"1.2","RAM":"1"}|,
{"id":"20","Name":"nx","Clock":"2.80","RAM":"8"}|,
{"id":"21","Name":"Autodesk Inventor","Clock":"3.3","RAM":"16"}|,
{"id":"22","Name":"pro engineer","Clock":"2.4","RAM":"1"}|,
{"id":"23","Name":"Autodesk 3ds Max","Clock":"2.80","RAM":"4"}|,
{"id":"24","Name":"solid edge","Clock":"2.80","RAM":"4"}|,
{"id":"25","Name":"edgecam","Clock":"2.80","RAM":"4"}|,
{"id":"26","Name":"Blender","Clock":"2.80","RAM":"8"}|,
{"id":"27","Name":"ANSYS","Clock":"2.80","RAM":"8"}|,
{"id":"28","Name":"SketchUp","Clock":"2.4","RAM":"8"}|,
{"id":"29","Name":"Revit","Clock":"2.5","RAM":"8"}|,
{"id":"30","Name":"LabVIEW","Clock":"2.80","RAM":"1"}|,
{"id":"31","Name":"SprutCAM","Clock":"2.80","RAM":"8"}|,
{"id":"32","Name":"GStarCAD","Clock":"2","RAM":"4"}|,
{"id":"33","Name":"ArtCAM","Clock":"2.80","RAM":"8"}|,
{"id":"34","Name":"progecad","Clock":"2","RAM":"4"}|,
{"id":"35","Name":"Autodesk Maya","Clock":"2.80","RAM":"8"}|,
{"id":"36","Name":"NetSuite","Clock":"1","RAM":"1"}|,
{"id":"37","Name":"Fishbowl Inventory","Clock":"2.3","RAM":"8"}|,
{"id":"38","Name":"Odoo","Clock":"2.80","RAM":"4"}|,
{"id":"39","Name":"Logility","Clock":"2.80","RAM":"1"}|,
{"id":"40","Name":"SYSPRO","Clock":"2.80","RAM":"1"}|,
{"id":"41","Name":"Plex Systems","Clock":"2.80","RAM":"1"}|,
{"id":"42","Name":"SkuVault","Clock":"2.80","RAM":"1"}|,
{"id":"43","Name":"Sage 300 Cloud","Clock":"2.80","RAM":"1"}|,
{"id":"44","Name":"Access ERP","Clock":"2.80","RAM":"1"}|,
{"id":"45","Name":"aACE","Clock":"2.80","RAM":"1"}|,
{"id":"46","Name":"GEP SMART","Clock":"2.80","RAM":"1"}|,
{"id":"47","Name":"Iptor","Clock":"2.80","RAM":"1"}|,
{"id":"48","Name":"Ramco SCM","Clock":"2.80","RAM":"1"}|,
{"id":"49","Name":"Click Reply","Clock":"2","RAM":"2"}|,
{"id":"50","Name":"Ariba","Clock":"2.80","RAM":"1"}|,
{"id":"51","Name":"SAP Business ByDesign","Clock":"2.80","RAM":"1"}|,
{"id":"52","Name":"IQNavigator","Clock":"2.80","RAM":"1"}|,
{"id":"53","Name":"QuickBooks Commerce","Clock":"2.80","RAM":"1"}|,
{"id":"54","Name":"Kuebix","Clock":"2.80","RAM":"1"}|,
{"id":"55","Name":"Jaggaer","Clock":"2.80","RAM":"1"}|,
{"id":"56","Name":"Magaya","Clock":"2.80","RAM":"1"}|,
{"id":"57","Name":"RizePoint","Clock":"2.80","RAM":"1"}|,
{"id":"58","Name":"xTuple","Clock":"2.80","RAM":"2"}|,
{"id":"59","Name":"aimms","Clock":"2.80","RAM":"16"}|,
{"id":"60","Name":"Dynamics 365 Finance","Clock":"2.80","RAM":"1"}|,
{"id":"61","Name":"RFgen","Clock":"2.80","RAM":"1"}|,
{"id":"62","Name":"Infoplus","Clock":"2.80","RAM":"1"}|,
{"id":"63","Name":"LogiView","Clock":"2.80","RAM":"1"}|,
{"id":"64","Name":"GAINSystems","Clock":"2.80","RAM":"1"}|,
{"id":"65","Name":"Da Vinci Supply Chain Business Suite","Clock":"2.80","RAM":"1"}|,
{"id":"66","Name":"Ultriva","Clock":"2.80","RAM":"1"}|,
{"id":"67","Name":"Visibility ERP","Clock":"2.80","RAM":"1"}|,
{"id":"68","Name":"Kechie","Clock":"2.80","RAM":"1"}|,
{"id":"69","Name":"DynaSys","Clock":"2.80","RAM":"1"}|,
{"id":"70","Name":"Royal 4 ERP","Clock":"2.80","RAM":"1"}|,
{"id":"71","Name":"ProMan ERP","Clock":"2.80","RAM":"1"}|,
{"id":"72","Name":"DTAILS","Clock":"2.80","RAM":"1"}|,
{"id":"73","Name":"iM3 SCM","Clock":"2.80","RAM":"1"}
"""
        var json2 = """
{"Title":"Processor_Base_Frequency(GHz)","nr_1":"2.128727273","nr_2":"2.33372549","nr_3":"2.431363636","nr_4":"2.295123967","nr_5":"2.632073171","nr_6":"2.520212766","nr_7":"2.2884","nr_8":"2.4575","nr_9":"3.26","nr_10":"3.075","nr_11":"2.9","nr_12":"3.78"}|,
{"Title":"nb_of_Cores","nr_1":"1.945454545","nr_2":"2.519607843","nr_3":"2.727272727","nr_4":"2.512396694","nr_5":"2.707317073","nr_6":"2.723404255","nr_7":"03.04","nr_8":"2.9","nr_9":"5.2","nr_10":"6","nr_11":"6.4","nr_12":"6.4"}|,
{"Title":"Cache(MB)","nr_1":"3.454545455","nr_2":"3.87254902","nr_3":"4.090909091","nr_4":"3.842975207","nr_5":"4.073170732","nr_6":"4.457446809","nr_7":"4.64","nr_8":"4.9","nr_9":"8.4","nr_10":"10.25","nr_11":"11.6","nr_12":"12.4"}|,
{"Title":"TDP(W)","nr_1":"35.37636364","nr_2":"46.50980392","nr_3":"41.73863636","nr_4":"34.90082645","nr_5":"41.11585366","nr_6":"32.43617021","nr_7":"28.98","nr_8":"26.325","nr_9":"65","nr_10":"51.75","nr_11":"61","nr_12":"53"}|,
{"Title":"Lithography(nm)","nr_1":"32.70909091","nr_2":"32","nr_3":"25.29545455","nr_4":"22.16528926","nr_5":"21.90243902","nr_6":"14.76595745","nr_7":"14.32","nr_8":"14","nr_9":"14","nr_10":"13","nr_11":"14","nr_12":"14"}|,
{"Title":"Graphics_Base_Frequency(MHz)","nr_1":"422.7272727","nr_2":"644","nr_3":"605.5681818","nr_4":"355.6859504","nr_5":"312.1707317","nr_6":"320.212766","nr_7":"355.52","nr_8":"322.5","nr_9":"350","nr_10":"337.5","nr_11":"350","nr_12":"340"}|,
{"Title":"T(°C)","nr_1":"94.65090909","nr_2":"85.74803922","nr_3":"88.00795455","nr_4":"91.1114876","nr_5":"85.0047561","nr_6":"93.36808511","nr_7":"96.76","nr_8":"99.7","nr_9":"72","nr_10":"79","nr_11":"100","nr_12":"100"}|,
{"Title":"Max_Memory_Bandwidth(GB/s)","nr_1":"15.84727273","nr_2":"20.33137255","nr_3":"23.56704545","nr_4":"25.35123967","nr_5":"24.68439024","nr_6":"30.16276596","nr_7":"33.244","nr_8":"33.5625","nr_9":"39.96","nr_10":"45.775","nr_11":"44.12","nr_12":"50.82"}"""
        // Podział JSON do listy
        val strs = json.split("|,").toTypedArray()
        val strs2 = json2.split("|,").toTypedArray()

        val mapper = jacksonObjectMapper()
        // Pobiranie listy z bazy danych
        var value = dataManager.allModules()

        // Pętla dla listy otrzymanej z JSON
        for(item in strs){
            // Dodanie danych w formularz Module
            val userFromJson = mapper.readValue<Module>(item)
            // Funkcja warunkowa dla sprawdzenia czy jest dany rekord w bazie danych
            if(!value.contains(userFromJson)){
                // Przy spelnaniu warunku wykonana funkcja dodania do bazy danych
                dataManager.add(userFromJson)
            }
        }
/*
        for(item in strs2){
            val userFromJson = mapper.readValue<DataStatics>(item)
            var value = dataManager.allStatistics()
            if(!value.contains(userFromJson)){
                dataManager.addStatics(userFromJson)
            }
        }
*/
        // Otwarzenie manageru fragmentów
        var tx =fManager.beginTransaction()
        // Dodawanie informacji, który fragment będzie otwarty
        tx.add(R.id.frag,Menu())
        tx.addToBackStack(null)
        // Wykonanie otworzenia fragmentu
        tx.commit()

    }

}
/*
* """
{"id":"1","Name":"SAP Supply Chain","Clock":"2.80","RAM":"1"}|,
{"id":"2","Name":"Oracle SCM Cloud","Clock":"2.80","RAM":"1"}|,
{"id":"3","Name":"Epicor SCM","Clock":"2.80","RAM":"1"}|,
{"id":"4","Name":"Infor Nexus","Clock":"2.80","RAM":"1"}|,
{"id":"5","Name":"Infor Supply Chain Management","Clock":"2.80","RAM":"1"}|,
{"id":"6","Name":"Sage Business Cloud X3","Clock":"2.80","RAM":"1"}|,
{"id":"7","Name":"Manhattan Supply Chain","Clock":"2.80","RAM":"1"}|,
{"id":"8","Name":"Blue Yonder","Clock":"2.80","RAM":"1"}|,
{"id":"9","Name":"Unison Planning","Clock":"2.80","RAM":"1"}|,
{"id":"10","Name":"Elementum","Clock":"2.80","RAM":"1"}|,
{"id":"11","Name":"MasterCam","Clock":"2.4","RAM":"8"}|,
{"id":"12","Name":"SolidWorks","Clock":"3.3","RAM":"16"}|,
{"id":"13","Name":"AutoCAD","Clock":"2.5","RAM":"16"}|,
{"id":"14","Name":"hypermill","Clock":"2.2","RAM":"16"}|,
{"id":"15","Name":"catia","Clock":"2.6","RAM":"4"}|,
{"id":"16","Name":"PowerMill","Clock":"2.6","RAM":"8"}|,
{"id":"17","Name":"Rhinoceros 3D","Clock":"2.6","RAM":"8"}|,
{"id":"18","Name":"FreeCAD","Clock":"2.80","RAM":"8"}|,
{"id":"19","Name":"linuxcnc","Clock":"1.2","RAM":"1"}|,
{"id":"20","Name":"nx","Clock":"2.80","RAM":"8"}|,
{"id":"21","Name":"Autodesk Inventor","Clock":"3.3","RAM":"16"}|,
{"id":"22","Name":"pro engineer","Clock":"2.4","RAM":"1"}|,
{"id":"23","Name":"Autodesk 3ds Max","Clock":"2.80","RAM":"4"}|,
{"id":"24","Name":"solid edge","Clock":"2.80","RAM":"4"}|,
{"id":"25","Name":"edgecam","Clock":"2.80","RAM":"4"}|,
{"id":"26","Name":"Blender","Clock":"2.80","RAM":"8"}|,
{"id":"27","Name":"ANSYS","Clock":"2.80","RAM":"8"}|,
{"id":"28","Name":"SketchUp","Clock":"2.4","RAM":"8"}|,
{"id":"29","Name":"Revit","Clock":"2.5","RAM":"8"}|,
{"id":"30","Name":"LabVIEW","Clock":"2.80","RAM":"1"}|,
{"id":"31","Name":"SprutCAM","Clock":"2.80","RAM":"8"}|,
{"id":"32","Name":"GStarCAD","Clock":"2","RAM":"4"}|,
{"id":"33","Name":"ArtCAM","Clock":"2.80","RAM":"8"}|,
{"id":"34","Name":"progecad","Clock":"2","RAM":"4"}|,
{"id":"35","Name":"Autodesk Maya","Clock":"2.80","RAM":"8"}|,
{"id":"36","Name":"NetSuite","Clock":"1","RAM":"1"}|,
{"id":"37","Name":"Fishbowl Inventory","Clock":"2.3","RAM":"8"}|,
{"id":"38","Name":"Odoo","Clock":"2.80","RAM":"4"}|,
{"id":"39","Name":"Logility","Clock":"2.80","RAM":"1"}|,
{"id":"40","Name":"SYSPRO","Clock":"2.80","RAM":"1"}|,
{"id":"41","Name":"Plex Systems","Clock":"2.80","RAM":"1"}|,
{"id":"42","Name":"SkuVault","Clock":"2.80","RAM":"1"}|,
{"id":"43","Name":"Sage 300 Cloud","Clock":"2.80","RAM":"1"}|,
{"id":"44","Name":"Access ERP","Clock":"2.80","RAM":"1"}|,
{"id":"45","Name":"aACE","Clock":"2.80","RAM":"1"}|,
{"id":"46","Name":"GEP SMART","Clock":"2.80","RAM":"1"}|,
{"id":"47","Name":"Iptor","Clock":"2.80","RAM":"1"}|,
{"id":"48","Name":"Ramco SCM","Clock":"2.80","RAM":"1"}|,
{"id":"49","Name":"Click Reply","Clock":"2","RAM":"2"}|,
{"id":"50","Name":"Ariba","Clock":"2.80","RAM":"1"}|,
{"id":"51","Name":"SAP Business ByDesign","Clock":"2.80","RAM":"1"}|,
{"id":"52","Name":"IQNavigator","Clock":"2.80","RAM":"1"}|,
{"id":"53","Name":"QuickBooks Commerce","Clock":"2.80","RAM":"1"}|,
{"id":"54","Name":"Kuebix","Clock":"2.80","RAM":"1"}|,
{"id":"55","Name":"Jaggaer","Clock":"2.80","RAM":"1"}|,
{"id":"56","Name":"Magaya","Clock":"2.80","RAM":"1"}|,
{"id":"57","Name":"RizePoint","Clock":"2.80","RAM":"1"}|,
{"id":"58","Name":"xTuple","Clock":"2.80","RAM":"2"}|,
{"id":"59","Name":"aimms","Clock":"2.80","RAM":"16"}|,
{"id":"60","Name":"Dynamics 365 Finance","Clock":"2.80","RAM":"1"}|,
{"id":"61","Name":"RFgen","Clock":"2.80","RAM":"1"}|,
{"id":"62","Name":"Infoplus","Clock":"2.80","RAM":"1"}|,
{"id":"63","Name":"LogiView","Clock":"2.80","RAM":"1"}|,
{"id":"64","Name":"GAINSystems","Clock":"2.80","RAM":"1"}|,
{"id":"65","Name":"Da Vinci Supply Chain Business Suite","Clock":"2.80","RAM":"1"}|,
{"id":"66","Name":"Ultriva","Clock":"2.80","RAM":"1"}|,
{"id":"67","Name":"Visibility ERP","Clock":"2.80","RAM":"1"}|,
{"id":"68","Name":"Kechie","Clock":"2.80","RAM":"1"}|,
{"id":"69","Name":"DynaSys","Clock":"2.80","RAM":"1"}|,
{"id":"70","Name":"Royal 4 ERP","Clock":"2.80","RAM":"1"}|,
{"id":"71","Name":"ProMan ERP","Clock":"2.80","RAM":"1"}|,
{"id":"72","Name":"DTAILS","Clock":"2.80","RAM":"1"}|,
{"id":"73","Name":"iM3 SCM","Clock":"2.80","RAM":"1"}
"""*/
/*
 * [
{"Name":"Processor_Base_Frequency(GHz)","nr_1":"2.128727273","nr_2":"2.33372549","nr_3":"2.431363636","nr_4":"2.295123967","nr_5":"2.632073171","nr_6":"2.520212766","nr_7":"2.2884","nr_8":"2.4575","nr_9":"3.26","nr_10":"3.075","nr_11":"2.9","nr_12":"3.78"},
{"Name":"nb_of_Cores","nr_1":"1.945454545","nr_2":"2.519607843","nr_3":"2.727272727","nr_4":"2.512396694","nr_5":"2.707317073","nr_6":"2.723404255","nr_7":"03.04","nr_8":"2.9","nr_9":"5.2","nr_10":"6","nr_11":"6.4","nr_12":"6.4"},
{"Name":"Cache(MB)","nr_1":"3.454545455","nr_2":"3.87254902","nr_3":"4.090909091","nr_4":"3.842975207","nr_5":"4.073170732","nr_6":"4.457446809","nr_7":"4.64","nr_8":"4.9","nr_9":"8.4","nr_10":"10.25","nr_11":"11.6","nr_12":"12.4"},
{"Name":"TDP(W)","nr_1":"35.37636364","nr_2":"46.50980392","nr_3":"41.73863636","nr_4":"34.90082645","nr_5":"41.11585366","nr_6":"32.43617021","nr_7":"28.98","nr_8":"26.325","nr_9":"65","nr_10":"51.75","nr_11":"61","nr_12":"53"},
{"Name":"Lithography(nm)","nr_1":"32.70909091","nr_2":"32","nr_3":"25.29545455","nr_4":"22.16528926","nr_5":"21.90243902","nr_6":"14.76595745","nr_7":"14.32","nr_8":"14","nr_9":"14","nr_10":"13","nr_11":"14","nr_12":"14"},
{"Name":"Graphics_Base_Frequency(MHz)","nr_1":"422.7272727","nr_2":"644","nr_3":"605.5681818","nr_4":"355.6859504","nr_5":"312.1707317","nr_6":"320.212766","nr_7":"355.52","nr_8":"322.5","nr_9":"350","nr_10":"337.5","nr_11":"350","nr_12":"340"},
{"Name":"T(°C)","nr_1":"94.65090909","nr_2":"85.74803922","nr_3":"88.00795455","nr_4":"91.1114876","nr_5":"85.0047561","nr_6":"93.36808511","nr_7":"96.76","nr_8":"99.7","nr_9":"72","nr_10":"79","nr_11":"100","nr_12":"100"}
,
{"Name":"Max_Memory_Bandwidth(GB/s)","nr_1":"15.84727273","nr_2":"20.33137255","nr_3":"23.56704545","nr_4":"25.35123967","nr_5":"24.68439024","nr_6":"30.16276596","nr_7":"33.244","nr_8":"33.5625","nr_9":"39.96","nr_10":"45.775","nr_11":"44.12","nr_12":"50.82"}]
 */
