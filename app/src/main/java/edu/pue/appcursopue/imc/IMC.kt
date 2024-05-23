package edu.pue.appcursopue.imc

import kotlin.math.roundToInt

class IMC (val persona: Persona) {

    fun imcNumerico ():Int
    {
        var imcNum:Int =0;
        var imcFloat: Float;

            //Índice de Masa Corporal  = peso / estatura al cuadrado
            imcFloat = this.persona.peso / (this.persona.estatura*this.persona.estatura)
            imcNum = imcFloat.roundToInt()


        return imcNum
    }

    fun imcNominal(imcNum:Int): ImcNominal {
        var imcNominal: ImcNominal;


        imcNominal = when {
            imcNum < 17 -> ImcNominal.DESNUTRIDO
            imcNum >= 17 && imcNum < 18 -> ImcNominal.DELGADO
            imcNum >= 18 && imcNum < 25 -> ImcNominal.IDEAL
            imcNum >= 25 && imcNum < 31 -> ImcNominal.SOBREPESO
            else -> ImcNominal.OBESO
        }

        return imcNominal
    }
}