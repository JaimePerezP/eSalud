Feature: Registrar cita solo si no existe otra cita a la misma hora y con el mismo médico

	Scenario Outline: <testCase> <resultadoEsperado>
	
	Given se introducen los datos de registro
		|	dni	| nombre  | apellidos   | pwd  | pwd2  | numTelefono  | email  | localidad  |  
    |<dni>| <nombre>| <apellidos> | <pwd>| <pwd2>| <numTelefono>| <email>| <localidad>|
	
	And inicia sesion
		| DNI |Contraseña|
		|<dni>|   <pwd>  |
		
	And vamos a registrar cita
	
	
	When indicamos los datos de la cita
		|	Tipo de Cita	| Especialidad  | Medico   | Fecha  | Hora  |  
    | <tipoDeCita>  | <especialidad>| <medico> | <fecha>| <hora>|
    
  Then se registra en la aplicacion si no se solapa con ninguna otra cita
	
	Examples: 
  	|testCase	| resultadoEsperado 			|	 tipoDeCita	    |    especialidad	   |     medico       |    fecha 		| hora  |
    |CASO 1		| Cita registrada				 	|   Cabecera	    |    Traumatologia   |  Sara Rodriguez  | 17/12/2019	| 8:30  |
    |CASO 2		|	Cita no disponible	    |   Enfermeria	  |    Traumatologia   |  Manuel Sarabia  | 12/12/2019	| 8:30	|
		
