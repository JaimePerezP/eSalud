Feature: Crear usuario solo si la contraseña es robusta

	Scenario Outline: <testCase> <resultadoEsperado>
	
	Given se registra un usuario de prueba
	|	dni			| nombre  		| apellidos  		 | pwd   				| numTelefono  	| email  								| localidad  	| rol 		| 
  |00000001 | Prueba      | Prueba Prueba  | Prueba-12345 | 777222111     | prueba@ejemplo.com 		|	Ciudad real	| paciente|
	
	When se introducen el rol de modificación
		|	rol	|
    |<rol>|
    
  Then se cambia el rol de ese usuario a gestor en la base de datos
	
	Examples: 
  	|testCase	| resultadoEsperado 			 |	rol	   |
    |CASO 1		| CONVERSION OK						 | admin	 |
		
