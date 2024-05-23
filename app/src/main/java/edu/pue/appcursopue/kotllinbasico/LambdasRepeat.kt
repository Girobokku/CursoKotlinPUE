package edu.pue.appcursopue.kotllinbasico

import edu.pue.appcursopue.imc.ImcNominal
import edu.pue.appcursopue.imc.Persona
import edu.pue.appcursopue.kotllinbasico.AlumnoBueno


fun mostrarNombre (nombre:String)
{
    println(nombre)
}
/*
fun main() {
    repeat(3) {
            i->mostrarNombre("pepe $i")
    }

    repeat(3, {i->mostrarNombre("pepe $i")})//HIgher Order Functions

    var n = 3
    var op:Operacion = () -> true
    saludo(5, op )

}*/

fun transforma (nombre:String, transformadora: (cad:String)->Int) :Int
{
    return transformadora(nombre)
}

fun numAlumnosBuenos (list: List<Alumno>, alumnoBueno: AlumnoBueno):Int
{
    var nAlumnosBuenos= 0

       nAlumnosBuenos = list.count { alumnoBueno.esAlumnoBueno(it) }

    return  nAlumnosBuenos

}

fun evaluarAlumnoBueno (alumno: Alumno, alumnoBueno: AlumnoBueno)
{
    var resultado = alumnoBueno.esAlumnoBueno(alumno)
    println("El alumno ${alumno.nombre} es bueno? $resultado")
}

fun main() {

   /* println(transforma("Vale", {c->c.length*2}))

    var alumnoNuevo = Alumno("Vale", 3)
    evaluarAlumnoBueno(alumnoNuevo , {a -> a.nota>9})
*/

    var alumno = Alumno ("Javi", 3)
    var alumno1 = Alumno ("Quique", 6)
    var alumno2 = Alumno ("Bea", 7)
    var alumno3 = Alumno ("Luis", 8)

    var listAlumnos = listOf(alumno, alumno1, alumno2, alumno3)

    val alumnoBueno = AlumnoBueno {a ->  a.nota > 7 }
    val alumnoBueno2 = AlumnoBueno {a ->  a.nota > 5 }
    var n = numAlumnosBuenos(listAlumnos, alumnoBueno)
    println("Según este criterio hay $n alumnos buenos ")

    var n2 = numAlumnosBuenos(listAlumnos, alumnoBueno2)
    println("Según este criterio 2 hay $n2 alumnos buenos ")
    //val alumnoBueno2 = AlumnoBueno {a ->  a.nota > 5 }

    /*listAlumnos.forEach({a-> println(a.nombre) })
    listAlumnos.forEach({println("nombre") })
    listAlumnos.forEach{println("nombre") }

    listAlumnos.forEach{
        //it es el alumno actual según recorro la colección
        println(it.toString())
    }*/

    /* var alumno = Alumno ("Javi", 3)
    var alumno1 = Alumno ("Quique", 6)
    var alumno2 = Alumno ("Bea", 7)
    var alumno3 = Alumno ("Luis", 8)

    //// Creating an instance using lambda
    //val isEven = IntPredicate { it % 2 == 0 }
    var listAlumnos = listOf(alumno, alumno1, alumno2, alumno3)

    val alumnoBueno = AlumnoBueno {a ->  a.nota > 7 }
    var n = numAlumnosBuenos(listAlumnos, alumnoBueno)
    println("Según $alumnoBueno hay ")
    val alumnoBueno2 = AlumnoBueno {a ->  a.nota > 5 }
*/
}

//SAM single Abstract Method
fun interface AlumnoBueno {
    fun esAlumnoBueno (alumno: Alumno):Boolean
}


