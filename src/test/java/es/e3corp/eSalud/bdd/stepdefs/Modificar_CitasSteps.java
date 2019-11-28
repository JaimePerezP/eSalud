package es.e3corp.eSalud.bdd.stepdefs;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Modificar_CitasSteps {

	ChromeDriver driver = WebDriver.webDriver;

	UsuarioRepository usuarioRepository;
	/*
	 * CitasRepository citasRepository; Cita cita = new Cita();
	 */
	List<Map<String, String>> a;

	@Given("el usuario se ha logueado y quiere cambiar los valores de los campos de la cita para actualizarlos")
	public void el_usuario_se_ha_logueado_y_quiere_cambiar_los_valores_de_los_campos_de_la_cita_para_actualizarlos(
			io.cucumber.datatable.DataTable dataTable) {

		driver.get("http://localhost:8080/auth/login");

		a = dataTable.asMaps(String.class, String.class);

		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("paciente"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
		driver.findElement(By.xpath("//input[@value='Acceder']")).click();
	}

	@When("el usuario paciente modifica los datos de una cita")
	public void el_usuario_paciente_modifica_los_datos_de_una_cita(io.cucumber.datatable.DataTable dataTable) {
		/*
		 * SUPUESTO: a la hora de modificar cita primero debes indicar como usuario que
		 * quieres modificar una cita seleccionando Modificar cita y posteriormente
		 * aseguras la modificacion pulsado en Modificar.
		 */
		driver.findElement(By.xpath("//input[@value='Lista de citas']")).click();
		driver.findElement(By.xpath("//input[@value='Modificar cita']")).click();

		a = dataTable.asMaps(String.class, String.class);
		/*
		 * cita.setPaciente(a.get(0).get("paciente"));
		 * cita.setTipo(a.get(0).get("tipo")); cita.setFecha(a.get(0).get("fecha"));
		 * cita.setHora(a.get(0).get("hora")); cita.setCentro(a.get(0).get("centro"));
		 * cita.setMedico(a.get(0).get("médico"));
		 * 
		 * driver.findElement(By.xpath("//input[@placeholder='form_paciente']")).clear()
		 * ; driver.findElement(By.xpath("//input[@placeholder='form_paciente']")).
		 * sendKeys(cita.getPaciente());
		 * 
		 * driver.findElement(By.xpath("//input[@placeholder='form_tipo']")).clear();
		 * driver.findElement(By.xpath("//input[@placeholder='form_tipo']")).sendKeys(
		 * cita.getTipo());
		 * 
		 * driver.findElement(By.xpath("//input[@placeholder='form_fecha']")).clear();
		 * driver.findElement(By.xpath("//input[@placeholder='form_fecha']")).sendKeys(
		 * cita.getFecha());
		 * 
		 * driver.findElement(By.xpath("//input[@placeholder='form_hora']")).clear();
		 * driver.findElement(By.xpath("//input[@placeholder='form_hora']")).sendKeys(
		 * cita.getHora());
		 * 
		 * driver.findElement(By.xpath("//input[@placeholder='form_centro']")).clear();
		 * driver.findElement(By.xpath("//input[@placeholder='form_centro']")).sendKeys(
		 * cita.getCentro());
		 * 
		 * driver.findElement(By.xpath("//input[@placeholder='form_medico']")).clear();
		 * driver.findElement(By.xpath("//input[@placeholder='form_médico']")).sendKeys(
		 * cita.getMedico());
		 */

	}

	@Then("se modifica la cita y se sustituye en la base de datos {string}")
	public void se_modifica_la_cita_y_se_sustituye_en_la_base_de_datos(String string) {
		driver.findElement(By.xpath("//input[@value='Modificar']")).click();
		/*
		 * switch (a.get(0).get("testCase")) { case "No tipo presente": assertTrue(cita
		 * == null); break; case "No fecha presente": assertTrue(cita == null); break;
		 * case "No hora presente": assertTrue(cita == null); break; case
		 * "No centro presente": assertTrue(cita == null); break; case
		 * "No medico presente": assertTrue(cita == null); break; case
		 * "Fecha incorrecta": assertTrue(cita == null); break; case "Hora incorrecta":
		 * assertTrue(cita == null); break; case "Paciente inexistente":
		 * assertEquals(usuarioRepository.findByDniAndContraseña(a.get(0).get("paciente"
		 * ), a.get(0).get("contraseña")), null); break; case "En el resto de casos":
		 * citasRepository.updateCita(cita); assertEquals(cita.getId(),
		 * citasRepository.findById(cita.getId).getId());
		 * citasRepository.deleteCita(cita.getId()); break; }
		 */
		driver.close();
	}
}