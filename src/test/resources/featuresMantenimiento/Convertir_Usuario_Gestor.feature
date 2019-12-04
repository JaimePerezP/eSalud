Feature: Convertir usuario a gestor por parte del gestor

	Scenario Outline: <testCase> <resultadoEsperado>
	
	Given se registra un usuario de prueba
	|	dni			| nombre  		| apellidos  		 | pwd   				| numTelefono  	| email  								| localidad  	| rol 		| 
  |00000003 | Prueba      | Prueba Prueba  | Prueba-12345 | 777222111     | prueba@ejemplo.com 		|	Ciudad real	| paciente|
	
	When se introducen el rol de modificación
		|	rol	|
    |<rol>|
    
  Then se cambia el rol de ese usuario a gestor en la base de datos
	
	Examples: 
  	|testCase	| resultadoEsperado 			 |	rol	   |
    |CASO 1		| CONVERSION OK						 | admin	 |
		
