package edu.pue.appcursopue.kotllinbasico

class Alumno {

    private var notaNominal: TipoNota? =null
        get() = TipoNota.traduceNotaNumericaATipoNota(this.nota)

    var nombre:String = ""
        get() {
            println("Llamando al getter de nombre")
            return field
        }

        //get() = field

        set(value) {
            println("Llamando al setter de nombre")
            field = value
        }

    var nota: Int = 0
        get() {
           // println("Llamando al getter de nota")
            return field
        }



        set(value) {
            println("Llamando al setter de nota")
            field = value

        }

    constructor(nombre: String, nota: Int) {
        this.nombre = nombre
        this.nota = nota
    }

    override fun toString(): String {
        println("Llamando a toString()")
        return "$nombre $nota $notaNominal"
    }
}