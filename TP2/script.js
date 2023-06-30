function enviarOperacion() {
    var operacion = document.getElementById("operacion").value;
  
    $.ajax({
      url: "calcular.php",
      type: "POST",
      data: { operacion: operacion },
      dataType: "json",
      success: function(response) {
        var resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = JSON.stringify(response);
  
        if (response.error) {
          resultadoDiv.classList.add("error");
        } else {
          resultadoDiv.classList.remove("error");
        }
      }
    });
  }
  
  function mostrarProduccion() {
    var produccionDiv = document.getElementById("produccion");
    produccionDiv.innerHTML = "<pre>S -> NOTN; | NDTZ; | N;\nT -> ep | NOT | NDT \nN -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 \nO -> + | - | *\nD -> /\nZ -> 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 </pre>";
  }
 
 
