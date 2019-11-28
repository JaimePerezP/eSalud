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

public class Listar_Citas_MedicoSteps {

  @Autowired
  UsuarioRepository usuarioRepository;
  // @Autowired
  // CitasRepository citasRepository;
  ChromeDriver driver = WebDriver.webDriver;
  List<Map<String, String>> a;
  Usuario u;

  @Given("un usuario que se loguea como médico")
  public void un_usuario_que_se_loguea_como_médico(io.cucumber.datatable.DataTable dataTable) {
    driver.get("http://localhost:8080/auth/login");

    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("medico"));
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();
  }

  @When("el medico pulsa en lista de citas")
  public void el_usuario_pulsa_en_lista_de_citas() {
    driver.findElement(By.xpath("//input[@value='Lista de citas']")).click();
  }

  @Then("se muestra la lista de citas del medico {string}")
  public void se_muestra_la_lista_de_citas_del_medico(String string) {
    switch (a.get(0).get("testCase")) {
    case "Dni y contraseña no introducido":
      assertEquals(usuarioRepository.findByDniAndContrasena(a.get(0).get("medico"), a.get(0).get("contraseña")), null);
      break;
    case "Dni no introducido":
      assertEquals(usuarioRepository.findByDniAndContrasena(a.get(0).get("medico"), a.get(0).get("contraseña")), null);
      break;
    case "Contraseña no introducida ":
      assertEquals(usuarioRepository.findByDniAndContrasena(a.get(0).get("medico"), a.get(0).get("contraseña")), null);
      break;
    case "Contraseña incorrecta":
      assertEquals(usuarioRepository.findByDniAndContrasena(a.get(0).get("medico"), a.get(0).get("contraseña")), null);
      break;
    case "Medico incorrecto":
      assertEquals(usuarioRepository.findByDniAndContrasena(a.get(0).get("medico"), a.get(0).get("contraseña")), null);
      break;
    case "Resto de casos":
      assertTrue(usuarioRepository.findByDniAndContrasena(a.get(0).get("medico"), a.get(0).get("contraseña")) != null);
      break;
    }

    try {
      Thread.sleep(7000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.close();
  }

}