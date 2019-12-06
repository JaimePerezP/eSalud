import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatListModule, MatIconModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatToolbarModule, MatTableModule, MatSortModule, MatPaginatorModule } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AppComponent } from './app.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { SidenavAdminComponent } from './components/sidenavAdmin/sidenavAdmin.component';
import { ListadoCitasComponent } from './components/listado-citas/listado-citas.component';
import { ModificarMedicoComponent } from './components/admin-ModificarMedico/ModificarMedico.component';
import { ModificarPacienteComponent } from './components/admin-ModificarPaciente/ModificarPaciente.component';
import { RegistrarEspecialidadComponent } from './components/admin-RegistrarEspecialidad/RegistrarEspecialidad.component';
import { RegistrarAdminComponent } from './components/admin-RegistrarAdmin/RegistrarAdmin.component';
import { RegistrarMedicoComponent } from './components/admin-RegistrarMedico/RegistrarMedico.component';
import { RegistrarPacienteComponent } from './components/admin-RegistrarPaciente/RegistrarPaciente.component';
import { DialogBoxPacienteComponent } from './components/dialog-box-paciente/dialog-box-paciente.component';
import { DialogBoxMedicoComponent } from './components/dialog-box-medico/dialog-box-medico.component';
import { DialogBoxModificarCita} from './components/dialog-box-modificarCita/dialog-box-modificarCita.component';
import { AlertComponent } from './components/alert/alert.component';
import { RegistrarCitaComponent } from './components/paciente-RegistrarCita/RegistrarCita.component';
import { MostrarPersonalMedicoComponent } from './components/admin-MostrarPersonalMedico/MostrarPersonalMedico.component';
import { SidenavMedicoComponent } from './components/sidenavMedico/sidenavMedico.component';
import { MostrarCitasComponent } from './components/medico-MostrarCitas/MostrarCitas.component';
import { MostrarEspecialidadesComponent} from './components/admin-MostrarEspecialidades/MostrarEspecialidades.component';
import { DialogBoxModificarEspecialidadComponent } from './components/dialog-box-modificarEspecialidad/dialog-box-modificarEspecialidad.component';

@NgModule({
  declarations: [
    AppComponent,
    SidenavComponent,
    SidenavAdminComponent,
    ListadoCitasComponent,
    ModificarMedicoComponent,
    ModificarPacienteComponent,
    RegistrarAdminComponent,
    RegistrarMedicoComponent,
    RegistrarPacienteComponent,
    DialogBoxPacienteComponent,
    DialogBoxMedicoComponent,
	  DialogBoxModificarCita,
    AlertComponent,
    RegistrarCitaComponent,
    MostrarPersonalMedicoComponent,
    SidenavMedicoComponent,
    MostrarCitasComponent,
    MostrarEspecialidadesComponent,
    DialogBoxModificarEspecialidadComponent,
    RegistrarEspecialidadComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatToolbarModule,
    FormsModule,
    MatDialogModule,
	MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    HttpClientModule
  ],
  entryComponents: [
    DialogBoxPacienteComponent,
    DialogBoxMedicoComponent,
    DialogBoxModificarCita,
    DialogBoxModificarEspecialidadComponent,

  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
