Feature: Crear usuario solo si la contraseña es robusta

	Scenario Outline: <testCase> <resultadoEsperado>
	
	Given se abre navegador y se va a pantalla de registro
	
	When se introducen los datos de registro
		|	dni	| nombre  | apellidos   | pwd  | pwd2  | numTelefono  | email  | localidad  |  
    |<dni>| <nombre>| <apellidos> | <pwd>| <pwd2>| <numTelefono>| <email>| <localidad>| 
    
  Then se registra en la aplicacion
	
	Examples: 
  	|testCase	| resultadoEsperado 			 |	 dni	  | nombre	  	| apellidos      | pwd 					| pwd2  				| numTelefono    | email             |  localidad   |  
    |CASO 1		| REGISTRO OK				 			 | 00000001	| Prueba      | Prueba Bien    | Prueba-12345	| Prueba-12345  | 645612514      | prueba@prueba.com |	Ciudad real	|
    |CASO 2		|	FALLO, CONTRASEÑA DEBIL	 | 00000002	| Prueba      | Prueba Mal     | pru					| pru						| 645612514      | prueba@prueba.com |	Ciudad real	| 
		
