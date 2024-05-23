package edu.pue.appcursopue.kotllinbasico

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main ()
{
    println("HOLA1")//IU
    runBlocking { //sección "suspendible"- puede ejecutarse en segundo plano o en paralelo
        launch {//el trabajo paralelo en sí conectarse al servidor
            delay(5000L) //simulamos un retardo
            println("HOLA2")
        }
        println("HOLA3")//IU
    }
    //println("HOLA2")


}