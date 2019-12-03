package es.e3corp.eSalud.bdd.stepdefs;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cambio_ventanasSteps {
	
	ChromeDriver driver = WebDriver.webDriver;
	List<Map<String, String>> tabla;

	@Given("se registra el usuario")
	public void se_registra_el_usuario(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		
		
		tabla = dataTable.asMaps(String.class, String.class);
		    
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//input[@placeholder='Nombre']")).sendKeys(tabla.get(0).get("nombre"));
		driver.findElement(By.xpath("//input[@placeholder='Apellidos']")).sendKeys(tabla.get(0).get("apellidos"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(tabla.get(0).get("pwd"));
		driver.findElement(By.xpath("//input[@placeholder='Teléfono']")).sendKeys(tabla.get(0).get("numTelefono"));
		driver.findElement(By.xpath("//input[@placeholder='Correo electrónico']")).sendKeys(tabla.get(0).get("email"));
		driver.findElement(By.xpath("//input[@placeholder='Localidad']")).sendKeys(tabla.get(0).get("localidad"));	
		driver.findElement(By.xpath("//input[@placeholder='Rol']")).sendKeys(tabla.get(0).get("rol"));
		driver.findElement(By.xpath("//input[@placeholder='Especialidad']")).sendKeys(tabla.get(0).get("especialidad"));	
		
		
		//throw new cucumber.api.PendingException();
	}

	@Given("se abre navegador y se va a pantalla de iniciar sesion")
	public void se_abre_navegador_y_se_va_a_pantalla_de_iniciar_sesion() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@Given("se introduce los datos de inicio de sesion del usuario")
	public void se_introduce_los_datos_de_inicio_de_sesion_del_usuario(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		
		
		tabla = dataTable.asMaps(String.class, String.class);
	    
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(tabla.get(0).get("pwd"));
		
		
		//throw new cucumber.api.PendingException();
	}

	@When("se abre la vista correspondiente al usuario")
	public void se_abre_la_vista_correspondiente_al_usuario(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		
		
		tabla = dataTable.asMaps(String.class, String.class);
	    
		driver.findElement(By.xpath("//input[@placeholder='Rol']")).sendKeys(tabla.get(0).get("rol"));
		
		
		//throw new cucumber.api.PendingException();
	}

	@When("se pulsa el boton de cambio de vista")
	public void se_pulsa_el_boton_de_cambio_de_vista(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		
		
		tabla = dataTable.asMaps(String.class, String.class);
	    
		driver.findElement(By.xpath("//input[@placeholder='Rol']")).sendKeys(tabla.get(0).get("rol"));
		
		
		//throw new cucumber.api.PendingException();
	}

	@When("se abre la vista de paciente")
	public void se_abre_la_vista_de_paciente() {
		// Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@When("se pulsa boton de volver a la vista correspondiente al usuario")
	public void se_pulsa_boton_de_volver_a_la_vista_correspondiente_al_usuario(
			io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		
		
		tabla = dataTable.asMaps(String.class, String.class);
	    
		driver.findElement(By.xpath("//input[@placeholder='Rol']")).sendKeys(tabla.get(0).get("rol"));
		
		
		//throw new cucumber.api.PendingException();
	}

	@Then("vuelve a la vista correspondiente al usuario")
	public void vuelve_a_la_vista_correspondiente_al_usuario(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		
		
		tabla = dataTable.asMaps(String.class, String.class);
	    
		driver.findElement(By.xpath("//input[@placeholder='Rol']")).sendKeys(tabla.get(0).get("rol"));
		
		
		//throw new cucumber.api.PendingException();
	}

}
