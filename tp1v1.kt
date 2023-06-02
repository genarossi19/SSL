import kotlin.random.Random

const val M = 20
const val m = 1

fun main() {
    var opcion = 0
    while(opcion!=5){


        mostrarMenu()
        opcion = readLine()?.toInt() ?: 0
        when (opcion){
            1 ->{
                val a = "a"
                val b = "b"
                val exponenteA = "ⁿ"
                val exponenteB = "²ⁿ"
                val condicion = "n > 0"

                val mensaje = "El lenguaje a utilizar es: L = { $a$exponenteA $b$exponenteB | $condicion }"
                println(mensaje)
                PAUSE()
            }



            2-> {
                println("Ingrese una cadena:")
                val cadena_completa = readLine()
                val esValida= cadena_completa?.let { analizarCadenaCompleta(it) }

                if (esValida == true) {
                    println("La cadena cumple con las condiciones.")
                    PAUSE()
                    clearConsole()
                } else {
                    println("La cadena no cumple con las condiciones.")
                    PAUSE()
                    clearConsole()
                }
            }

            3->{
                analizarPasoAPaso()
            }

            4-> {
                val cadeEjemplo = darCadenaEjemplo()
                println("Cadena de ejemplo: ${cadeEjemplo}")
                PAUSE()
                clearConsole()
            }




            5 -> {
                println("HASTA LUEGO")
                PAUSE()
                clearConsole()
            }
            else ->{
                println(" ELEGI UNA OPCION VALIDA")
                PAUSE()
                clearConsole()
            }
        }



    }


}

fun mostrarMenu(){
    println("\n1: MOSTRAR EL LENGUAJE")
    println("\n2: ANALIZAR CADENA COMPLETA")
    println("\n3: ANALIZAR CADENA PASO A PASO")
    println("\n4: DAR UNA CADENA DE EJEMPLO")
    println("\n5: SALIR")
}

fun PAUSE(){
    println("Presiona Enter para continuar...")
    readLine()
}

fun darCadenaEjemplo(): String {
    val n = Random.nextInt(m, M)
    var cadenaEjemplo: String = ""



    repeat(n) {
        cadenaEjemplo += "a"
    }

    repeat(2 * n) {
        cadenaEjemplo += "b"
    }

    return cadenaEjemplo
}

fun analizarCadenaCompleta(cadena: String): Boolean {
    val regex = Regex("[ab]+") // Patrón regex para verificar que la cadena solo tenga "a" y "b"
    var countA = 0 // Cuenta la cantidad de 'a' en la cadena
    var countB = 0 // Cuenta la cantidad de 'b' en la cadena

    for (c in cadena) //'c' representa cada caracter de la cadena en ese momento del bucle
    {
        when (c) {
            'a' -> {
                if (countB > 0) {
                    // Si hay 'b' antes de 'a', la cadena no cumple con la condición
                    return false
                }
                countA++
            }
            'b' -> {
                countB++
            }
            else -> {
                // Si hay un carácter diferente de 'a' y 'b', la cadena no cumple con la condición
                return false
            }
        }
    }

    return regex.matches(cadena) && // Verifica que la cadena solo tenga "a" y "b"
            countB == 2 * countA // Verifica que la cantidad de 'b' sea el doble de la cantidad de 'a'
}
fun clearConsole() {
    val lines =25
    repeat(lines) { println() }
}

fun analizarPasoAPaso() {
    val cadenaCompleta = StringBuilder() //para almacenar la cadena completa que el usuario va ingresando
    var cadenaValida = true
    var contadorA = 0
    var contadorB = 0

    println("Ingrese la cadena letra por letra (escriba 'fin' para finalizar):")

    while (true) {
        val input = readLine()
        if (input == "fin") {
            break
        }

        if (input != "a" && input != "b") {
            println("La cadena solo puede contener 'a' y 'b'.")
            cadenaValida = false
            break
        }

        cadenaCompleta.append(input) //agrega la entrada input al final de la cadena que se está construyendo en el StringBuilder

        if (input == "a") {
            contadorA++
        } else {
            contadorB++
        }

        if (contadorB > contadorA * 2) {
            println("La cantidad de 'b' no puede ser mayor al doble de la cantidad de 'a'.")
            cadenaValida = false
            break
        }
    }

    if (cadenaValida && contadorA > 0 && contadorB == contadorA * 2) {
        println("La cadena cumple con todas las condiciones.")
    } else {
        println("La cadena no cumple con las condiciones.")
    }
}
