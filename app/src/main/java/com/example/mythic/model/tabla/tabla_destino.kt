package com.example.mythic.model.tabla

public class tablaDestino(){
    val valores = arrayOf(
        arrayOf(arrayOf(10,50,91),arrayOf(5,25,86),arrayOf(2,10,83),arrayOf(1,5,82),arrayOf(1,5,82),arrayOf(0,0,81),arrayOf(0,0,81),arrayOf(0,-20,77),arrayOf(0,-20,77),arrayOf(0,-40,73),arrayOf(0,-40,73),arrayOf(0,-55,70),arrayOf(0,-65,68)),
        arrayOf(arrayOf(15,75,96),arrayOf(50), arrayOf(25), arrayOf(15), arrayOf(10),  arrayOf(5),   arrayOf(5),   arrayOf(0),  arrayOf(0), arrayOf(-20), arrayOf(-20), arrayOf(-35), arrayOf(-45),
        arrayOf(arrayOf(18,90,99),arrayOf(75), arrayOf(50), arrayOf(35), arrayOf(25), arrayOf(15),  arrayOf(10),   arrayOf(5),   arrayOf(5),   arrayOf(0),   arrayOf(0), arrayOf(-15), arrayOf(-25),
        arrayOf(arrayOf(19,95,100),arrayOf(85), arrayOf(65), arrayOf(50), arrayOf(45), arrayOf(25),  arrayOf(15),  arrayOf(10),   arrayOf(5),   arrayOf(5),   arrayOf(5),  arrayOf(-5), arrayOf(-15),
        arrayOf(arrayOf(20,100,0),arrayOf(90), arrayOf(75), arrayOf(55), arrayOf(50), arrayOf(35),  arrayOf(20),  arrayOf(15),  arrayOf(10),   arrayOf(5),   arrayOf(5),   arrayOf(0), arrayOf(-10),
        arrayOf(arrayOf(21,105,0), arrayOf(95), arrayOf(85), arrayOf(75), arrayOf(65), arrayOf(50),  arrayOf(35),  arrayOf(25),  arrayOf(15),  arrayOf(10),  arrayOf(10),   arrayOf(5),  arrayOf(-5),
        arrayOf(arrayOf(22,110,0), arrayOf(95), arrayOf(90), arrayOf(85), arrayOf(80), arrayOf(65),  arrayOf(50),  arrayOf(45),  arrayOf(25),  arrayOf(20),  arrayOf(15),   arrayOf(5),   arrayOf(0),
        arrayOf(arrayOf(23,115,0),arrayOf(100), arrayOf(95), arrayOf(90), arrayOf(85), arrayOf(75),  arrayOf(55),  arrayOf(50),  arrayOf(35),  arrayOf(25),  arrayOf(20),  arrayOf(10),   arrayOf(5)),
        arrayOf(arrayOf(24,120,0),arrayOf(105), arrayOf(95), arrayOf(95), arrayOf(90), arrayOf(85),  arrayOf(75),  arrayOf(65),  arrayOf(50),  arrayOf(45),  arrayOf(35),  arrayOf(15),   arrayOf(5)),
        arrayOf(arrayOf(25,125,0),arrayOf(115),arrayOf(100), arrayOf(95), arrayOf(95),arrayOf( 90),  arrayOf(80),  arrayOf(75),  arrayOf(55),  arrayOf(50),  arrayOf(45),  arrayOf(20),  arrayOf(10)),
        arrayOf(arrayOf(26,130,0),arrayOf(125),arrayOf(110), arrayOf(95), arrayOf(95), arrayOf(90),  arrayOf(85),  arrayOf(80),  arrayOf(65),  arrayOf(55),  arrayOf(50),  arrayOf(25),  arrayOf(10)),
        arrayOf(arrayOf(30,150,0),arrayOf(145),arrayOf(130),arrayOf(100),arrayOf(100), arrayOf(95),  arrayOf(95),  arrayOf(90),  arrayOf(85),  arrayOf(80),  arrayOf(75),  arrayOf(50),  arrayOf(25)),
        arrayOf(arrayOf(34,170,0),arrayOf(165),arrayOf(150),arrayOf(120),arrayOf(120),arrayOf(100), arrayOf(100),  arrayOf(95),  arrayOf(95),  arrayOf(90),  arrayOf(90),  arrayOf(75),  arrayOf(50))

    public fun obtenerRespuestaProbabilidad(tirada: Int, rangoProbabilidadSI : Int, caos : Int = 0) : String{

        if(tirada <= valores[obtenerIndiceTablaSegunRangoProbabilidadSI(rangoProbabilidadSI)][obtenerIndiceTablaSegunRangoCaos(caos)][1] ){
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
