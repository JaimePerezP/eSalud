package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registro_Usuarios_GestorSteps {

	List<Map<String, String>> tabla;
	ChromeDriver driver = WebDriver.webDriver;
	@Autowired
	UsuarioRepository ur;

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
		ur.saveUsuario(usuario);
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
		tabla = dataTable.asMaps(String.class, String.class);
		driver.findElement(By.xpath("//*[@id=\"dni\"]")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(tabla.get(0).get("pwd"));
		driver.findElement(By.xpath("/html/body/linl/app-root/app-login/div/form/div/div/p[1]/input")).click();
	}

	@When("entra en la vista del gestor")
	public void entra_en_la_vista_del_gestor() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		assertEquals(
				driver.findElementByXPath("/html/body/linl/app-root/app-sidenavadmin/div/mat-toolbar/h1").getText(),
				"Administrador");
	}

	@When("se va a la ventana de registro del gestor")
	public void se_va_a_la_ventana_de_registro_del_gestor(io.cucumber.datatable.DataTable dataTable) {
		tabla = dataTable.asMaps(String.class, String.class);
		if (tabla.get(0).get("resultadoEsperado").equals("REGISTRO MEDICO OK")) {
			driver.get("http://localhost:8080/admin/RegistrarMedico");
		} else if (tabla.get(0).get("resultadoEsperado").equals("REGISTRO PACIENTE OK")) {

		} else if (tabla.get(0).get("resultadoEsperado").equals("REGISTRO ADMIN OK")) {

		}
	}

	@Then("el gestor lo registra en la aplicacion")
	public void el_gestor_lo_registra_en_la_aplicacion(io.cucumber.datatable.DataTable dataTable) {
		tabla = dataTable.asMaps(String.class, String.class);
		if (tabla.get(0).get("resultadoEsperado").equals("REGISTRO MEDICO OK")) {
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[1]/div[2]/input")
					.sendKeys(tabla.get(0).get("dni"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[2]/div[2]/input")
					.sendKeys(tabla.get(0).get("nombre"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[3]/div[2]/input")
					.sendKeys(tabla.get(0).get("apellidos"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[4]/div[2]/input")
					.sendKeys(tabla.get(0).get("pwd"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[5]/div[2]/select")
					.sendKeys(tabla.get(0).get("especialidad"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[6]/div[2]/input")
					.sendKeys(tabla.get(0).get("centro"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[7]/div[2]/input")
					.sendKeys(tabla.get(0).get("numTelefono"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[1]/div[8]/div[2]/input")
					.sendKeys(tabla.get(0).get("email"));
			driver.findElementByXPath(
					"/html/body/linl/app-root/app-sidenavadmin/div/mat-sidenav-container/mat-sidenav-content/app-registrarmedico/div/form/div[2]/p[1]/input")
					.click();
		}
	}

}
