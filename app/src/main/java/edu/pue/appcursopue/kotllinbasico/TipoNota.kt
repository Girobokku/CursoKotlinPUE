package edu.pue.appcursopue.kotllinbasico

enum class TipoNota {
    SUSPENSO, APROBADO, BIEN, NOTABLE, SOBRESALIENTE;

    companion object {

        fun traduceNotaNumericaATipoNota (notaNumerica:Int): TipoNota
        {
            var tipoNota =
                when(notaNumerica)
                {
                    in 0..4 -> SUSPENSO
                    5 -> APROBADO
                    6 -> BIEN
                    in 7..8 -> NOTABLE
                    in 9..10 -> SOBRESALIENTE
                    else -> APROBADO
                }
            return tipoNota
        }
   }

}

