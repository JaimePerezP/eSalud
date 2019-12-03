package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.collect.Table;

import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Control_Solape_CitasSteps {
	
	ChromeDriver driver = WebDriver.webDriver;
	UsuarioRepository ur;
	List<Map<String, String>> tabla;
	
	@Given("se registra un usuario")
	public void se_registra_un_usuario(io.cucumber.datatable.DataTable dataTable) {
		driver.get("localhost:8080/auth/register");
		
		tabla = dataTable.asMaps(String.class, String.class);
		
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(tabla.get(0).get("pwd"));
		driver.findElement(By.xpath("//input[@placeholder='Repita su contraseña']")).sendKeys(tabla.get(0).get("pwd2"));
		driver.findElement(By.xpath("//input[@placeholder='Nombre']")).sendKeys(tabla.get(0).get("nombre"));
		driver.findElement(By.xpath("//input[@placeholder='Apellidos']")).sendKeys(tabla.get(0).get("apellidos"));
		driver.findElement(By.xpath("//input[@placeholder='Teléfono']")).sendKeys(tabla.get(0).get("numTelefono"));
		driver.findElement(By.xpath("//input[@placeholder='Correo electrónico']")).sendKeys(tabla.get(0).get("email"));
		driver.findElement(By.xpath("//input[@placeholder='Localidad']")).sendKeys(tabla.get(0).get("localidad"));
		
		driver.findElement(By.xpath("//input[@value='Registrarse']")).click();
		
	}
	
	@Given("inicia sesion")
	public void inicia_sesion(io.cucumber.datatable.DataTable dataTable) {
		driver.get("localhost:8080/auth/login");
		
		tabla = dataTable.asMaps(String.class, String.class);
		
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(tabla.get(0).get("pwd"));
		
		driver.findElement(By.xpath("//input[@value='Acceder']")).click();
		
	}
	
	@Given("vamos a registrar cita")
	public void inicia_sesion() {
		driver.get("localhost:8080/auth/RegistrarCita");
	}

	@When("indicamos los datos de la cita")
	public void se_introducen_los_datos_de_registro(io.cucumber.datatable.DataTable dataTable) {
		
		tabla = dataTable.asMaps(String.class, String.class);
	    
		driver.findElement(By.xpath("//input[@placeholder='Tipo de Cita']")).sendKeys(tabla.get(0).get("tipo de cita"));
		driver.findElement(By.xpath("//input[@placeholder='Especialidad']")).sendKeys(tabla.get(0).get("especialidad"));
		driver.findElement(By.xpath("//input[@placeholder='Médico']")).sendKeys(tabla.get(0).get("medico"));
		driver.findElement(By.xpath("//input[@placeholder='Fecha']")).sendKeys(tabla.get(0).get("fecha"));
		driver.findElement(By.xpath("//input[@placeholder='Hora']")).sendKeys(tabla.get(0).get("hora"));
		
		driver.findElement(By.xpath("//input[@value='Registrar cita']")).click();
		
	
	}

	@Then("se registra en la aplicacion si no se solapa con ninguna otra cita")
	public void se_registra_en_la_aplicacion() {
		driver.findElement(By.xpath("//input[@value='Registrar cita']")).click();;
		
		if (tabla.get(0).get("resultadoEsperado") == "Cita registrada") {
			assertEquals(tabla.get(0).get("id"), this.ur.findOne(tabla.get(0).get("id")));
			this.ur.deleteUsuario(tabla.get(0).get("id"));
		} 
		else if(tabla.get(0).get("resultadoEsperado") == "Cita no disponible"){
			assertNull(this.ur.findOne(tabla.get(0).get("id")));
		}
	}


}
