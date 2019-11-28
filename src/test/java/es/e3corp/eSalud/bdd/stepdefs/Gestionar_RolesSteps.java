
package es.e3corp.eSalud.bdd.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Gestionar_RolesSteps {
  ChromeDriver driver = WebDriver.webDriver;
  List<Map<String, String>> a;
  UsuarioRepository ur;

  @Given("iniciamos la aplicación y nos dirigimos a registrar personal medico")
  public void iniciamos_la_aplicación_y_nos_dirigimos_a_registrar_personal_medico() {

    driver.get("http://localhost:8080/auth/login");

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys("root");
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();

    driver.findElement(By.xpath("//input[@value='Registrar personal medico']")).click();
  }

  @When("introducimos los datos de registro del personal medico")
  public void introducimos_los_datos_de_registro_del_personal_medico(io.cucumber.datatable.DataTable dataTable) {
    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("dni"));
    driver.findElement(By.xpath("//input[@placeholder='Nombre']")).sendKeys(a.get(0).get("nombre"));
    driver.findElement(By.xpath("//input[@placeholder='Apellidos']")).sendKeys(a.get(0).get("apellidos"));
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
    driver.findElement(By.xpath("//input[@placeholder='Rol']")).sendKeys(a.get(0).get("rol"));
    driver.findElement(By.xpath("//input[@placeholder='Centro']")).sendKeys(a.get(0).get("centro"));
    driver.findElement(By.xpath("//input[@placeholder='Teléfono']")).sendKeys(a.get(0).get("numTelefono"));
    driver.findElement(By.xpath("//input[@placeholder='Correo electrónico']")).sendKeys(a.get(0).get("email"));
    driver.findElement(By.xpath("//input[@placeholder='Especialidad']")).sendKeys(a.get(0).get("especialidad"));
    driver.findElement(By.xpath("//input[@placeholder='Localidad']")).sendKeys(a.get(0).get("localidad"));
  }

  @Then("registramos un tipo de personal medico en la aplicacion")
  public void registramos_un_tipo_de_personal_medico_en_la_aplicacion() {
    
    driver.findElement(By.xpath("//input[@value='Registrar']")).click();

    if ((a.get(0).get("resultadoEsperado") == "REGISTRO MEDICO CORRECTO")
        || (a.get(0).get("resultadoEsperado") == "REGISTRO ENFERMERO CORRECTO")
        || (a.get(0).get("resultadoEsperado") == "REGISTRO ENFERMERO CORRECTO")) {
      assertEquals(a.get(0).get("dni"), this.ur.findOne(a.get(0).get("dni")));
      this.ur.deleteUsuario(a.get(0).get("dni"));
    }

    driver.close();
  }
}