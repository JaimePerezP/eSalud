import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SidenavComponent } from './components/sidenav/sidenav.component';
import { SidenavAdminComponent } from './components/sidenavAdmin/sidenavAdmin.component';
import { ListadoCitasComponent } from './components/listado-citas/listado-citas.component';
import { ModificarMedicoComponent } from './components/admin-ModificarMedico/ModificarMedico.component';
import { ModificarPacienteComponent } from './components/admin-ModificarPaciente/ModificarPaciente.component';
import { RegistrarEspecialidadComponent } from './components/admin-RegistrarEspecialidad/RegistrarEspecialidad.component';
import { RegistrarAdminComponent } from './components/admin-RegistrarAdmin/RegistrarAdmin.component';
import { RegistrarMedicoComponent } from './components/admin-RegistrarMedico/RegistrarMedico.component';
import { RegistrarPacienteComponent } from './components/admin-RegistrarPaciente/RegistrarPaciente.component';
import { RegistrarCitaComponent } from './components/paciente-RegistrarCita/RegistrarCita.component';
import { MostrarPersonalMedicoComponent } from './components/admin-MostrarPersonalMedico/MostrarPersonalMedico.component';

import { AuthGuard } from './_helpers';
import { SidenavMedicoComponent } from './components/sidenavMedico/sidenavMedico.component';
import { MostrarCitasComponent } from './components/medico-MostrarCitas/MostrarCitas.component';
import { MostrarEspecialidadesComponent} from './components/admin-MostrarEspecialidades/MostrarEspecialidades.component';


import { from } from 'rxjs';



const routes: Routes = [

  { path: '', redirectTo: '/auth/login', pathMatch: 'full' },
  { path: 'auth', loadChildren: './components/auth/auth.module#AuthModule' },
  { path: 'citas/RegistrarCita/citas', redirectTo: 'citas' },
  { path: 'citas/RegistrarCita/RegistrarCita', redirectTo: 'citas/RegistrarCita' },
  { path: 'citas/citas', redirectTo: 'citas' },
  { path: 'citas/Cambio', redirectTo: 'medico'},
  {
    path: 'citas', component: SidenavComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: ListadoCitasComponent }
    ]
  },
  {
    path: 'citas/RegistrarCita', component: SidenavComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: RegistrarCitaComponent }
    ]
  },

  {
    path: 'admin', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: ModificarMedicoComponent }
    ]
  },
  /********************************************************************************** */
  {
    path: 'admin/ModificarMedico', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: ModificarMedicoComponent }
    ]
  },
  { path: 'admin/ModificarMedico/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/ModificarMedico/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/ModificarMedico/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/ModificarMedico/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/ModificarMedico/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/ModificarMedico/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/ModificarMedico/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/ModificarMedico/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },


  /********************************************************************************** */
  {
    path: 'admin/ModificarPaciente', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: ModificarPacienteComponent }
    ]
  },
  { path: 'admin/ModificarPaciente/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/ModificarPaciente/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/ModificarPaciente/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/ModificarPaciente/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/ModificarPaciente/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/ModificarPaciente/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/ModificarPaciente/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/ModificarPaciente/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },

  /********************************************************************************** */
  {
    path: 'admin/RegistrarEspecialidad', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: RegistrarEspecialidadComponent }
    ]
  },
  { path: 'admin/RegistrarEspecialidad/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/RegistrarEspecialidad/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/RegistrarEspecialidad/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/RegistrarEspecialidad/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/RegistrarEspecialidad/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/RegistrarEspecialidad/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/RegistrarEspecialidad/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/RegistrarEspecialidad/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },
  /********************************************************************************** */
  {
    path: 'admin/RegistrarAdmin', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: RegistrarAdminComponent }
    ]
  },
  { path: 'admin/RegistrarAdmin/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/RegistrarAdmin/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/RegistrarAdmin/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/RegistrarAdmin/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/RegistrarAdmin/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/RegistrarAdmin/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/RegistrarAdmin/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/RegistrarAdmin/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },
/********************************************************************************** */
  {
    path: 'admin/RegistrarPaciente', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: RegistrarPacienteComponent }
    ]
  },
  { path: 'admin/RegistrarPaciente/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/RegistrarPaciente/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/RegistrarPaciente/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/RegistrarPaciente/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/RegistrarPaciente/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/RegistrarPaciente/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/RegistrarPaciente/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/RegistrarPaciente/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },
  /********************************************************************************** */
  {
    path: 'admin/RegistrarMedico', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: RegistrarMedicoComponent }
    ]
  },
  { path: 'admin/RegistrarMedico/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/RegistrarMedico/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/RegistrarMedico/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/RegistrarMedico/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/RegistrarMedico/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/RegistrarMedico/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/RegistrarMedico/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/RegistrarMedico/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },
  /********************************************************************************** */
  {
    path: 'admin/RegistrarPaciente', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: RegistrarPacienteComponent }
    ]
  },
  { path: 'admin/RegistrarPaciente/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/RegistrarPaciente/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/RegistrarPaciente/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/RegistrarPaciente/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/RegistrarPaciente/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/RegistrarPaciente/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/RegistrarPaciente/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/RegistrarPaciente/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },
  /********************************************************************************** */
  {
    path: 'admin/MostrarEspecialidades', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: MostrarEspecialidadesComponent }
    ]
  },
  { path: 'admin/MostrarEspecialidades/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/MostrarEspecialidades/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/MostrarEspecialidades/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/MostrarEspecialidades/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/MostrarEspecialidades/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/MostrarEspecialidades/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  { path: 'admin/MostrarEspecialidades/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },

  /********************************************************************************** */
   {
    path: 'admin/MostrarPersonalMedico', component: SidenavAdminComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: MostrarPersonalMedicoComponent }
    ]
  },
  { path: 'admin/MostrarPersonalMedico/ModificarMedico', redirectTo: 'admin/ModificarMedico' },
  { path: 'admin/MostrarPersonalMedico/RegistrarEspecialidad', redirectTo: 'admin/RegistrarEspecialidad' },
  { path: 'admin/MostrarPersonalMedico/ModificarPaciente', redirectTo: 'admin/ModificarPaciente' },
  { path: 'admin/MostrarPersonalMedico/RegistrarAdmin', redirectTo: 'admin/RegistrarAdmin' },
  { path: 'admin/MostrarPersonalMedico/RegistrarMedico', redirectTo: 'admin/RegistrarMedico' },
  { path: 'admin/MostrarPersonalMedico/RegistrarPaciente', redirectTo: 'admin/RegistrarPaciente' },
  { path: 'admin/MostrarPersonalMedico/MostrarEspecialidades', redirectTo: 'admin/MostrarEspecialidades' },
  { path: 'admin/MostrarPersonalMedico/MostrarPersonalMedico', redirectTo: 'admin/MostrarPersonalMedico' },
  {
    path: 'medico', component: SidenavMedicoComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: MostrarCitasComponent }
    ]
  },
  {
    path: 'medico/MostrarCitas', component: SidenavMedicoComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: MostrarCitasComponent }
    ]
  },  
  { path: 'medico/MostrarCitas/MostrarCitas', redirectTo: 'medico/MostrarCitas' },
  { path: 'medico/Cambio', redirectTo: 'citas' },
  { path: 'medico/Cambio/Cambio', redirectTo: 'citas' },
  { path: 'medico/MostrarCitas/Cambio', redirectTo: 'citas' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
