package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Cambio_ventanasSteps {

	ChromeDriver driver = WebDriver.webDriver;
	List<Map<String, String>> tabla;
	@Autowired
	UsuarioRepository ur;

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

	@Given("se abre navegador y se va a pantalla de iniciar sesion")
	public void se_abre_navegador_y_se_va_a_pantalla_de_iniciar_sesion() {
		driver.get("http://localhost:8080/auth/login");
	}

	@Given("se introduce los datos de inicio de sesion del usuario")
	public void se_introduce_los_datos_de_inicio_de_sesion_del_usuario(io.cucumber.datatable.DataTable dataTable) {
		tabla = dataTable.asMaps(String.class, String.class);
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(tabla.get(0).get("dni"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(tabla.get(0).get("pwd"));
		driver.findElement(By.xpath("/html/body/linl/app-root/app-login/div/form/div/div/p[1]/input")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		if (tabla.get(0).get("rol").equals("admin")) {
			driver.get("http://localhost:8080/admin");
		} else if (tabla.get(0).get("rol").equals("medico")) {
			driver.get("http://localhost:8080/medico");
		} else if (tabla.get(0).get("rol").equals("paciente")) {
			driver.get("http://localhost:8080/citas");
		}
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
		if (tabla.get(0).get("rol").equals("admin")) {
			driver.get("http://localhost:8080/citas");
		} else if (tabla.get(0).get("rol").equals("medico")) {
			driver.get("http://localhost:8080/citas");
		} else if (tabla.get(0).get("rol").equals("paciente")) {
			driver.get("http://localhost:8080/citas");
		}
	}

	@When("se abre la vista de paciente")
	public void se_abre_la_vista_de_paciente() {
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/citas");
	}

}
