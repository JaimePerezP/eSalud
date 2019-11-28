package es.e3corp.eSalud.bdd.oldStepdefs;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import es.e3corp.eSalud.bdd.stepdefs.WebDriver;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PedirCitaSteps {

  ChromeDriver driver = WebDriver.webDriver;
  @Autowired
  UsuarioRepository usuarioRepository;
//    CitasRepository citasRepository;
//    Cita c = new Cita();
  List<Map<String, String>> a;

  @Given("un usuario logueado como paciente")
  public void un_usuario_logueado_como_paciente(io.cucumber.datatable.DataTable dataTable) {
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

  @When("el usuario rellena los campos de la cita")
  public void el_usuario_rellena_los_campos_de_la_cita(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    a = dataTable.asMaps(String.class, String.class);
//        c.setPaciente(a.get(0).get("paciente"));
//        c.setTipo(a.get(0).get("tipo"));
//        c.setFecha(a.get(0).get("fecha"));
//        c.setCentro(a.get(0).get("centro"));
//        c.setMedico(a.get(0).get("médico"));
//        c.setHora(a.get(0).get("hora"));
//        driver.findElement(By.xpath("//input[@placeholder='paciente']")).sendKeys(c.getPaciente());
//        driver.findElement(By.xpath("//input[@placeholder='tipo']")).sendKeys(c.getTipo());
//        driver.findElement(By.xpath("//input[@placeholder='fecha']")).sendKeys(c.getFecha());
//        driver.findElement(By.xpath("//input[@placeholder='centro']")).sendKeys(c.getCentro());
//        driver.findElement(By.xpath("//input[@placeholder='médico']")).sendKeys(c.getMedico());
//        driver.findElement(By.xpath("//input[@placeholder='hora']")).sendKeys(c.getHora());
  }

  @Then("la cita se guarda en la base de datos y se asocia con el paciente {string}")
  public void la_cita_se_guarda_en_la_base_de_datos_y_se_asocia_con_el_paciente(String string) {
    driver.findElement(By.xpath("//input[@value='Crear cita']")).click();
    /*
     * switch (a.get(0).get("testCase")) { case "SIN DNI INTRODUCIDO":
     * assertEquals(usuarioRepository.findByDniAndContraseña(a.get(0).get("paciente"
     * ), a.get(0).get("contraseña")), null); break; case
     * "SIN CONTRASEÑA INTRODUCIDA":
     * assertEquals(usuarioRepository.findByDniAndContraseña(a.get(0).get("paciente"
     * ), a.get(0).get("contraseña")), null); break; case "SIN TIPO INTRODUCIDO":
     * assertTrue(c == null); break; case "SIN FECHA INTRODUCIDA": assertTrue(c ==
     * null); break; case "SIN HORA INTRODUCIDA": assertTrue(c == null); break; case
     * "SIN CENTRO INTRODUCIDO": assertTrue(c == null); break; case
     * "SIN MÉDICO INTRODUCIDO": assertTrue(c == null); break; case
     * "CON FECHA ERRÓNEA": assertTrue(c == null); break; case "CON HORA ERRÓNEA":
     * assertTrue(c == null); break; case "CON PACIENTE INEXISTENTE":
     * assertEquals(usuarioRepository.findByDniAndContraseña(a.get(0).get("paciente"
     * ), a.get(0).get("contraseña")), null); break; case "CON MÉDICO INEXISTENTE":
     * assertTrue(!usuarioRepository.findOne(a.get(0).get("médico")).get().getRol().
     * equals("médico")); break; case "EN OTRO CASO": //
     * citasRepository.saveCita(c); // assertEquals(c.getId(),
     * citasRepository.findById(c.getId).getId()); //
     * citasRepository.deleteCita(c.getId()); break; } try { Thread.sleep(7000); }
     * catch (InterruptedException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); }
     */
    driver.close();
  }
}
