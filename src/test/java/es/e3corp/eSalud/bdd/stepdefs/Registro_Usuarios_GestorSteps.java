package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.e3corp.eSalud.Service.UsuarioService;
import es.e3corp.eSalud.model.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registro_Usuarios_GestorSteps {

	List<Map<String, String>> tabla;
	ChromeDriver driver = WebDriver.webDriver;
	UsuarioService usersService;

	@Given("se registra un gestor")
	public void se_registra_un_gestor(io.cucumber.datatable.DataTable dataTable) {
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
		Usuario usuario = new Usuario(dni, nombre, apellidos, pwd, rol, null, null, tlf, localidad, null, email);
		usersService.saveUsuario(usuario);
	}

	@Given("se abre navegador y se va a pantalla de inicio")
	public void se_abre_navegador_y_se_va_a_pantalla_de_inicio() {
		driver.get("http://localhost:8080/auth/login");
	}

	@Given("se introducen los datos de inicio de sesion del gestor")
	public void se_introducen_los_datos_de_inicio_de_sesion_del_gestor(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		driver.findElement(By.xpath("//*[@id=\"dni\"]")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(tabla.get(0).get("pwd"));
		driver.findElement(By.xpath("/html/body/linl/app-root/app-login/div/form/div/div/p[1]/input")).click();
	}

	@When("entra en la vista del gestor")
	public void entra_en_la_vista_del_gestor() {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElementByXPath("/html/body/linl/app-root/app-sidenavadmin/div/mat-toolbar/h1")));
		assertEquals(
				driver.findElementByXPath("/html/body/linl/app-root/app-sidenavadmin/div/mat-toolbar/h1").getText(),
				"Administrador");
	}

	@When("se va a la ventana de registro del gestor")
	public void se_va_a_la_ventana_de_registro_del_gestor() {
		if (tabla.get(0).get("resultadoEsperado") == "REGISTRO MEDICO OK") {
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav/div/mat-nav-list/a[3]/div/div[1]").click();
		}
	}

	@Then("el gestor lo registra en la aplicacion")
	public void el_gestor_lo_registra_en_la_aplicacion() {

	}

}
