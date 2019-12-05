Feature: Cambiar de la ventana de gestor a paciente y de paciente a gestor solo si es gestor y cambiar de medico a paciente y de paciente a medico solo si es medico

	Scenario Outline: <testCase> <resultadoEsperado>
	
	Given se registra el usuario
		|	dni	| nombre  | apellidos   | pwd  | numTelefono  | email  | localidad  | rol   | especialidad   |
    |<dni>| <nombre>| <apellidos> | <pwd>| <numTelefono>| <email>| <localidad>| <rol> | <especialidad> |
 
	And se abre navegador y se va a pantalla de iniciar sesion
		
	And se introduce los datos de inicio de sesion del usuario
		|	dni	| pwd  |
    |<dni>| <pwd>|
		
	When se abre la vista correspondiente al usuario
    | rol   |
    | <rol> |
    
  And se pulsa el boton de cambio de vista
    | rol   |
    | <rol> |  
    
  And se abre la vista de paciente
  
  And se pulsa boton de volver a la vista correspondiente al usuario
    | rol   |
    | <rol> |
    
  Then vuelve a la vista correspondiente al usuario
    | rol   |
    | <rol> |

	
	Examples: 
  	|testCase	| resultadoEsperado 	 |	 dni	  | nombre	  	| apellidos      | pwd 					| numTelefono    | email             |  localidad   | rol      | especialidad   | 
    |CASO 1		| Medico    				 	 | 00000001	| Prueba      | Prueba Bien    | Prueba-12345	| 645612514      | prueba@prueba.com |	Ciudad real	| medico   | Podólogo       |
    |CASO 2		| Gestor	             | 00000002	| Prueba      | Prueba Mal     | pru					| 645612514      | prueba@prueba.com |	Ciudad real	| admin    |                |
    |CASO 3		| Paciente   				 	 | 00000003	| Prueba      | Prueba Bien    | Prueba-12345	| 645612514      | prueba@prueba.com |	Ciudad real	| paciente |                |
		
	

