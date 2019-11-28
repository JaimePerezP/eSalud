package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registro {

	ChromeDriver driver = WebDriver.webDriver;
	UsuarioRepository ur;
	List<Map<String, String>> a;

	@Given("abrimos el navegador e iniciamos la pantalla de registro")
	public void abrimos_el_navegador_e_iniciamos_la_pantalla_de_registro() throws MalformedURLException {

//        ClassLoader classLoader = getClass().getClassLoader();
//        String filePath = classLoader.getResource("chromedriver.exe").getFile();
//        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(filePath))
//                .build();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--no-sandbox", "--verbose", "--headless", "--disable-web-security",
//                "--ignore-certificate-errors", "--allow-running-insecure-content", "--allow-insecure-localhost",
//                "--disable-gpu");
		driver.get("localhost:8080/auth/register");

	}

	@When("introducimos los datos de registro")
	public void introducimos_los_datos_de_registro(io.cucumber.datatable.DataTable dataTable) {

		a = dataTable.asMaps(String.class, String.class);

		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("dni"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
		driver.findElement(By.xpath("//input[@placeholder='Repita su contraseña']"))
				.sendKeys(a.get(0).get("contraseña2"));
		driver.findElement(By.xpath("//input[@placeholder='Correo electrónico']")).sendKeys(a.get(0).get("email"));
		driver.findElement(By.xpath("//input[@placeholder='Localidad']")).sendKeys(a.get(0).get("localidad"));
		driver.findElement(By.xpath("//input[@placeholder='Apellidos']")).sendKeys(a.get(0).get("apellidos"));
		driver.findElement(By.xpath("//input[@placeholder='Teléfono']")).sendKeys(a.get(0).get("numTelefono"));
		driver.findElement(By.xpath("//input[@placeholder='Nombre']")).sendKeys(a.get(0).get("nombre"));

	}

	@Then("nos registramos en la aplicacion")
	public void nos_registramos_en_la_aplicacion() {

		// En caso de caso de erro comprobar si el mensaje que se muestra es el correcto

		driver.findElement(By.xpath("//input[@value='Registrarse']")).click();

		if (a.get(0).get("resultadoEsperado") == "REGISTRO CORRECTO") {
			assertEquals(a.get(0).get("dni"), this.ur.findOne(a.get(0).get("dni")));
			this.ur.deleteUsuario(a.get(0).get("dni"));
		}

		driver.close();
	}

}
