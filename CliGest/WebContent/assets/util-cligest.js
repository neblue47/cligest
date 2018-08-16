 function retormaIMC(){
 	var peso    = $('#peso').val();
 	var altura  = $('#altura').val();
 	if(altura === '')
 		altura = 1;
 	if(peso === '')
 		peso = 0;
 	altura = altura * altura;
 	var imc = Number(peso) / Number(altura);
 	$('#imc').val(imc.toFixed(1));
 }