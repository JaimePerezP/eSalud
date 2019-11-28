package es.e3corp.eSalud.bdd.oldStepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.e3corp.eSalud.model.Usuario;
import es.e3corp.eSalud.repository.UsuarioRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CrearUsuarioSteps {

  @Autowired
  private UsuarioRepository usuarioRepository;
  private Usuario u = new Usuario();

  @Given("se registra un usuario con los siguientes campos")
  public void se_registra_un_usuario_paciente_con_los_siguientes_campos(DataTable dataTable) {
    /*
     * u = new Usuario("05706193P", "Hector", "Estoy hasta los huevos", "12343",
     * "paciente", "", "", 845302345, "Miguelturra", "coijenwa", "emai");
     */

    List<Map<String, String>> a = dataTable.asMaps(String.class, String.class);

    u.setDni(a.get(0).get("dni"));
    u.setNombre(a.get(0).get("nombre"));
    u.setApellidos(a.get(0).get("apellidos"));
    u.setcontrasena(a.get(0).get("contraseña"));
    u.setRol(a.get(0).get("rol"));
    u.setNumTelefono(a.get(0).get("numTelefono"));
    u.setEmail(a.get(0).get("email"));
    u.setEspecialidad(a.get(0).get("especialidad"));
    u.setLocalidad(a.get(0).get("localidad"));
    u.setMedico(a.get(0).get("medico"));
    u.setCentro(a.get(0).get("centro"));

  }

  @When("el usuario guarda sus datos con los campos requeridos")
  public void el_usuario_guarda_sus_datos_con_los_campos_requeridos() {
    this.usuarioRepository.saveUsuario(u);

  }

  @Then("el resultado de guardar el usuario es correcto")
  public void el_resultado_de_guardar_el_usuario_es_correcto() {
    assertEquals(u.getDni(), this.usuarioRepository.findByDniAndContrasena(u.getDni(), u.getcontrasena()).getDni());
    this.usuarioRepository.deleteUsuario(u.getId());
  }

}
