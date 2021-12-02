package com.example.mythic.model.tabla

public class tablaDestino(){
    val valores = arrayOf(  arrayOf( 50, 25, 10,  5,  5,  0,   0, -20, -20, -40, -40, -55, -65),
                            arrayOf( 75, 50, 25, 15, 10,  5,   5,   0,   0, -20, -20, -35, -45),
                            arrayOf( 90, 75, 50, 35, 25, 15,  10,   5,   5,   0,   0, -15, -25),
                            arrayOf( 95, 85, 65, 50, 45, 25,  15,  10,   5,   5,   5,  -5, -15),
                            arrayOf(100, 90, 75, 55, 50, 35,  20,  15,  10,   5,   5,   0, -10),
                            arrayOf(105, 95, 85, 75, 65, 50,  35,  25,  15,  10,  10,   5,  -5),
                            arrayOf(110, 95, 90, 85, 80, 65,  50,  45,  25,  20,  15,   5,   0),
                            arrayOf(115,100, 95, 90, 85, 75,  55,  50,  35,  25,  20,  10,   5),
                            arrayOf(120,105, 95, 95, 90, 85,  75,  65,  50,  45,  35,  15,   5),
                            arrayOf(125,115,100, 95, 95, 90,  80,  75,  55,  50,  45,  20,  10),
                            arrayOf(130,125,110, 95, 95, 90,  85,  80,  65,  55,  50,  25,  10),
                            arrayOf(150,145,130,100,100, 95,  95,  90,  85,  80,  75,  50,  25),
                            arrayOf(170,165,150,120,120,100, 100,  95,  95,  90,  90,  75,  50))

    public fun obtenerRespuestaProbabilidad(tirada: Int, rangoProbabilidadSI : Int, caos : Int = 0) : String{

        if(tirada <= valores[obtenerIndiceTablaSegunRangoProbabilidadSI(rangoProbabilidadSI)][obtenerIndiceTablaSegunRangoCaos(caos)] ){
            return "SI"
        }else{
            return "NO"
        }

    }

    private fun obtenerIndiceTablaSegunRangoProbabilidadSI (rangoProbabilidadSI: Int) : Int {
        return rangoProbabilidadSI + 5
    }

    private fun obtenerIndiceTablaSegunRangoCaos(caos : Int): Int {
        if((caos == 6)||(caos==5)||(caos==4)){
            return 5
        }else if((caos == 8)||(caos == 7)){
            return 4
        }else if((caos == 10)||(caos == 9)){
            return 3
        }else if((caos == 3)||(caos == 2)){
            return 6
        }else if(caos == 1){
            return 7
        }
        return 5
    }
}
