package es.e3corp.eSalud.bdd.stepdefs;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModificarTiemposConsultaSteps {

  WebDriver driver;
  List<Map<String, String>> a;
  // Especialidad e = new Especialidad();
  // @Autowired
  // EspecialidadController ec;

  @Given("un usuario logueado como gestor")
  public void un_usuario_logueado_como_gestor(io.cucumber.datatable.DataTable dataTable) {
    Path path = FileSystems.getDefault().getPath("src/test/resources/drivers/geckodriver.exe");
    System.setProperty("webdriver.gecko.driver", path.toString());
    FirefoxOptions fo = new FirefoxOptions();
    fo.addArguments("--headless");
    WebDriver driver = new FirefoxDriver(fo);
    driver.get("http://localhost:8080/auth/login");

    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("gestor"));
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();

    driver.findElement(By.xpath("//input[@value='Especialidades']")).click();

  }

  @When("el usuario seleccione una especialidad")
  public void el_usuario_seleccione_una_especialidad(io.cucumber.datatable.DataTable dataTable) {
    a = dataTable.asMaps(String.class, String.class);
    // e.setEspecialidad(a.get(0).get("especialidad"));
    // e.setTiempo(a.get(0).get("tiempo"));
    driver.findElement(By.xpath("//input[@placeholder='Especialidad']")).sendKeys(a.get(0).get("especialidad")); // ?
    driver.findElement(By.xpath("//input[@placeholder='Tiempo']")).sendKeys(a.get(0).get("tiempoConsulta"));
    driver.findElement(By.xpath("//input[@placeholder='Hora inicio']")).sendKeys(a.get(0).get("horaInicio"));
    driver.findElement(By.xpath("//input[@placeholder='Hora fin']")).sendKeys(a.get(0).get("horaFin"));
  }

  @Then("se mostrarán sus características y se podrá editar su tiempo de consulta {string}")
  public void se_mostrarán_sus_características_y_se_podrá_editar_su_tiempo_de_consulta(String string) {
    driver.findElement(By.xpath("//input[@value='Modificar']")).click();
    // ec.updateEspecialidad(e);
    driver.close();
  }
}
