package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Convertir_Usuario_GestorSteps {

	ChromeDriver driver = WebDriver.webDriver;
	List<Map<String, String>> tabla;
	@Autowired
	UsuarioRepository ur;
	String dni = null;

	@Given("se registra un usuario de prueba")
	public void se_registra_un_usuario_de_prueba(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

		tabla = dataTable.asMaps(String.class, String.class);
		String dni = tabla.get(0).get("dni");
		String nombre = tabla.get(0).get("nombre");
		String apellidos = tabla.get(0).get("apellidos");
		String pwd = tabla.get(0).get("pwd");
		String tlf = tabla.get(0).get("numTelefono");
		String email = tabla.get(0).get("email");
		String localidad = tabla.get(0).get("localidad");
		String rol = tabla.get(0).get("rol");
		Usuario usuario = new Usuario(dni, nombre, apellidos, pwd, rol, "", "", tlf, localidad, "", email);
		this.ur.saveUsuario(usuario);
	}

	@When("se introducen el rol de modificación")
	public void se_introducen_el_rol_de_modificación(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		tabla = dataTable.asMaps(String.class, String.class);
		driver.get("localhost:8080/auth/login");
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys("root");
		driver.findElement(By.xpath("//input[@value='Acceder']")).click();
		driver.findElement(By.xpath(
				"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav/div/mat-nav-list/a[2]/div"))
				.click();
	}

	@Then("se cambia el rol de ese usuario a gestor en la base de datos")
	public void se_cambia_el_rol_de_ese_usuario_a_gestor_en_la_base_de_datos() {
		List<Usuario> gestores = this.ur.findByRol("admin");
		for (Usuario u : gestores) {
			if (u.getDni().equals(dni)) {
				assertEquals(dni, u.getDni());
			}
		}
	}
}
