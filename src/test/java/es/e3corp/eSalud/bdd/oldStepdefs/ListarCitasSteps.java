package es.e3corp.eSalud.bdd.oldStepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import es.e3corp.eSalud.bdd.stepdefs.WebDriver;
import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ListarCitasSteps {

  @Autowired
  UsuarioRepository usuarioRepository;
  // @Autowired
  // CitasRepository citasRepository;
  ChromeDriver driver = WebDriver.webDriver;
  List<Map<String, String>> a;
  Usuario u;

  @Given("un usuario que se loguea como paciente")
  public void un_usuario_que_se_loguea_como_paciente(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    driver.get("http://localhost:8080/auth/login");

    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("paciente"));
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();
  }

  @When("el usuario pulsa en lista de citas")
  public void el_usuario_pulsa_en_lista_de_citas() {
    driver.findElement(By.xpath("//input[@value='Lista de citas']")).click();
  }

  @Then("se muestra la lista de citas del paciente {string}")
  public void se_muestra_la_lista_de_citas_del_paciente(String string) {
    switch (a.get(0).get("testCase")) {
    case "SIN DNI INTRODUCIDO":
      assertEquals(null,usuarioRepository.findByDniAndContrasena(a.get(0).get("paciente"), a.get(0).get("contraseña")));
      break;
    case "SIN CONTRASEÑA INTRODUCIDA":
      assertEquals(null, usuarioRepository.findByDniAndContrasena(a.get(0).get("paciente"), a.get(0).get("contraseña")));
      break;
    case "CON CONTRASEÑA ERRÓNEA":
      assertEquals(null, usuarioRepository.findByDniAndContrasena(a.get(0).get("paciente"), a.get(0).get("contraseña")));
      break;
    case "CON PACIENTE INEXISTENTE":
      assertEquals(null, usuarioRepository.findByDniAndContrasena(a.get(0).get("paciente"), a.get(0).get("contraseña")));
      break;
    case "EN OTRO CASO":
      assertTrue(
          usuarioRepository.findByDniAndContrasena(a.get(0).get("paciente"), a.get(0).get("contraseña")) != null);
      // Permite pasar y se listan las citas
      break;
    }

    try {
      Thread.sleep(7000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    driver.close();
  }
}
