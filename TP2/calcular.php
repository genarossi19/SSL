<?php
$operacion = $_POST['operacion'];

// Eliminar espacios en blanco de la operación
$operacion = str_replace(" ", "", $operacion);

$largo = strlen($operacion);
$valor_prev = null;

// Variable para almacenar el resultado o mensaje de error
$resultado = "";
$error = false;

// Validar la cadena de operación
if (
    $operacion[$largo - 1] == ";" &&                        // La cadena debe terminar con punto y coma (;)
    !preg_match('/[a-zA-Z]/', $operacion) &&                // No debe contener letras
    !preg_match('/(\d)(\d)/', substr($operacion, 0, -1)) && // No deben haber dos dígitos consecutivos
    $operacion[0] != "-" &&                                  // El primer carácter no puede ser un signo negativo
    is_numeric($operacion[$largo - 2]) &&                   // El penúltimo carácter debe ser un dígito
    !preg_match('/[()]/', $operacion) &&                   // No deben haber paréntesis en la operación
    substr_count($operacion, ';') == 1 &&                      // Solo debe haber un punto y coma en la cadena de operación
    !preg_match('/(\+{2}|\-{2}|\*{2}|\/{2})/', $operacion)  // No deben haber dos signos consecutivos
) {
    // Eliminar el último caracter, el punto y coma final, es para evaluarla como una expresión matemática a la cadena
    $operacion = substr($operacion, 0, -1);

    try {
        // Evaluar la operación matemática
        eval('$resultado = ' . $operacion . ';');
    } catch (DivisionByZeroError $e) {
        // Manejar el caso de división por cero
        $resultado = "No puedes dividir por cero";
        $error = true;
    }
} else {
    // Mostrar un mensaje de error si la cadena de operación no cumple con las condiciones
    $resultado = "La cadena no cumple con lo solicitado";
    $error = true;
}

// Crear un arreglo con el resultado, el estado de error y el estilo
$response = [
    'resultado' => $resultado,
    'error' => $error,
    
];

// Enviar la respuesta como JSON
echo json_encode($response);
?>
