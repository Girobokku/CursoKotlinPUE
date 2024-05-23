package edu.pue.appcursopue.kotllinbasico

fun main ()
{
    println("HOLA DESDE KOTLIN")
    val u: User = User("hola")
    println(u.firstName)
    val u2 = User("hola", "adios")
    val u3 = User(lastName = "saludo");
    //val u4 = User;

    val peces = arrayOf<String?>("tibu", "salmon", "trucha", null);
    peces.forEach {pez ->
        var a = pez?.length ?: 0;
        println(" la longitud de pez $pez es $a \$");
       // return 0;
    }

    val ap = peces.map { pez ->
        var a = pez?.length ?: 0;
        println(" la longitud de pez $pez es $a \$");
        pez+pez }

    ap.forEach{
        println(it)
    }

    print("noSeSabe = " + noSeSabe("hola", 'H'));
}


fun noSeSabe (cadena:String, letra:Char):Int {
    var pos:Int = 0;
    pos = with(cadena) {
        println("$this is ${length}")
        cadena.lastIndexOf(letra, 0, true);
    }
    return pos;
}
