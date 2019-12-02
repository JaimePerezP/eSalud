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

public class Pwd_RobustaSteps {
	
	ChromeDriver driver = WebDriver.webDriver;
	UsuarioRepository ur;
	List<Map<String, String>> tabla;
	
	@Given("se abre navegador y se va a pantalla de registro")
	public void se_abre_navegador_y_se_va_a_pantalla_de_registro() {
		driver.get("localhost:8080/auth/register");
	}

	@When("se introducen los datos de registro")
	public void se_introducen_los_datos_de_registro(io.cucumber.datatable.DataTable dataTable) {
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
		driver.findElement(By.xpath("//input[@placeholder='Repita su contraseña']")).sendKeys(tabla.get(0).get("pwd2"));
		driver.findElement(By.xpath("//input[@placeholder='Nombre']")).sendKeys(tabla.get(0).get("nombre"));
		driver.findElement(By.xpath("//input[@placeholder='Apellidos']")).sendKeys(tabla.get(0).get("apellidos"));
		driver.findElement(By.xpath("//input[@placeholder='Teléfono']")).sendKeys(tabla.get(0).get("numTelefono"));
		driver.findElement(By.xpath("//input[@placeholder='Correo electrónico']")).sendKeys(tabla.get(0).get("email"));
		driver.findElement(By.xpath("//input[@placeholder='Localidad']")).sendKeys(tabla.get(0).get("localidad"));	
	}

	@Then("se registra en la aplicacion")
	public void se_registra_en_la_aplicacion() {
		driver.findElement(By.xpath("//input[@value='Registrarse']")).click();
		
		if (tabla.get(0).get("resultadoEsperado") == "REGISTRO OK") {
			assertEquals(tabla.get(0).get("dni"), this.ur.findOne(tabla.get(0).get("dni")));
			this.ur.deleteUsuario(tabla.get(0).get("dni"));
		} 
		else if(tabla.get(0).get("resultadoEsperado") == "FALLO, CONTRASEÑA DEBIL"){
			assertNull(this.ur.findOne(tabla.get(0).get("dni")));
		}
	}


}
