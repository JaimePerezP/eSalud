package es.e3corp.eSalud.bdd.oldStepdefs;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import es.e3corp.eSalud.bdd.stepdefs.WebDriver;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Eliminar_CitasSteps {

  ChromeDriver driver = WebDriver.webDriver;
  List<Map<String, String>> a;
  UsuarioRepository usuarioRepository;
  /*
   * CitasRepository citasRepository; Cita cita = new
   * Cita("DATOS DE LA NUEVA CITA");
   */

  @Given("el usuario de ha logueado y elegirá la cita que quiera eliminar")
  public void el_usuario_de_ha_logueado_y_elegirá_la_cita_que_quiera_eliminar(
      io.cucumber.datatable.DataTable dataTable) {

    /* GUARDAR CITA FICTICIA EN LA BASE DE DATOS */

    driver.get("http://localhost:8080/auth/login");

    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys("11111111");
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys("123");
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();

  }

  @When("el usuario eliminará el usuario seleccionado")
  public void el_usuario_eliminará_el_usuario_seleccionado(io.cucumber.datatable.DataTable dataTable) {

    driver.findElement(By.xpath("//input[@value='Lista de citas']")).click();

    /* SELECCIONAR ELEMENTO DE LA TABLA DE LISTA */

    driver.findElement(By.xpath("//input[@value='Eliminar cita']")).click();

  }

  @Then("se elimina la cita de la base de datos. {string}")
  public void se_elimina_la_cita_de_la_base_de_datos(String string) {

    /* Comrobar que la cita que voy a borrar esta en la base de datos */
    driver.findElement(By.xpath("//input[@value='Eliminar']")).click();
    /* Comprobar que se ha eliminado */

    driver.close();
  }

}
