Feature: El gestor puede registrar usuarios

	Scenario Outline: <testCase> <resultadoEsperado>
	
	Given se registra un gestor
	|	 dni	   | nombre	  	| apellidos      | pwd 					| numTelefono    | email             |  localidad   | rol 	 |
  | 00000002 | Prueba     | Prueba Bien    | Prueba-12345	| 645612514      | prueba@prueba.com |	Ciudad real	| admin  |
	
	Given se abre navegador y se va a pantalla de inicio
	
	And se introducen los datos de inicio de sesion del gestor
		|	dni	    | pwd  				|  
    | 00000002| Prueba-12345|
    
  When entra en la vista del gestor
  
  And se va a la ventana de registro del gestor
  
  Then el gestor lo registra en la aplicacion
  |	 dni	| nombre	 | apellidos   | pwd 	 | numTelefono    | email   |  localidad  | rol 	 |
  | <dni> | <nombre> | <apellidos> | <pwd> | <numTelefono>  | <email> |	<localidad>	| <rol>  |
	
	Examples: 
  	|testCase	| resultadoEsperado 			 |	 dni	  | nombre	  	| apellidos      | pwd 					| pwd2  				| numTelefono    | email             |  localidad   | rol 	   | especialidad |
    |CASO 1		| REGISTRO MEDICO OK			 | 00000001	| Prueba      | Prueba Bien    | Prueba-12345	| Prueba-12345  | 645612514      | prueba@prueba.com |	Ciudad real	| medico   | Cabecera     |
    |CASO 2		| REGISTRO PACIENTE OK		 | 00000003	| Prueba      | Prueba Bien    | Prueba-12345	| Prueba-12345  | 645612514      | prueba@prueba.com |	Ciudad real	| paciente | Cabecera			|
    |CASO 3		| REGISTRO ADMIN OK		 		 | 00000004	| Prueba      | Prueba Bien    | Prueba-12345	| Prueba-12345  | 645612514      | prueba@prueba.com |	Ciudad real	| paciente | Cabecera			|
    |CASO 4		| FALLO, DNI EXISTENTE		 | 00000001	| Prueba      | Prueba Bien    | Prueba-12345	| Prueba-12345  | 645612514      | prueba@prueba.com |	Ciudad real	| medico   | Cabecera			|