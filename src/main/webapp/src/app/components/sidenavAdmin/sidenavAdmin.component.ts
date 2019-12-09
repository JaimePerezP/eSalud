import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { AuthService } from 'src/app/_services';
import { AlertService } from 'src/app/_services/alert.service';


@Component({
  selector: 'app-sidenavAdmin',
  templateUrl: './sidenavAdmin.component.html',
  styleUrls: ['./sidenavAdmin.component.css']
})
export class SidenavAdminComponent implements OnInit {

  mobileQuery: MediaQueryList;

  fillerNav = [
    { name: "Lista de médicos", route: "ModificarMedico", icon: "list" },
    { name: "Lista de pacientes", route: "ModificarPaciente", icon: "edit" },
    { name: "Registrar administradores", route: "RegistrarAdmin", icon: "autorenew" },
    { name: "Registrar médicos", route: "RegistrarMedico", icon: "autorenew" },
    { name: "Registrar pacientes", route: "RegistrarPaciente", icon: "autorenew" },
    { name: "Filtrar personal médico", route: "MostrarPersonalMedico", icon: "edit" },
    { name: "Gestión de especialidades", route: "MostrarEspecialidades", icon: "edit" },
    { name: "Registrar especialidad", route: "RegistrarEspecialidad", icon: "autorenew" },
    { name: "Cambiar a modo paciente", route: "Cambio", icon: "supervisor_account" },
    { name: "Salir", route: "/", icon: "logout" }

  ]

  fillerContent = Array.from({ length: 50 }, () =>
    `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
       labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
       laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
       voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
       cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.`);

  private _mobileQueryListener: () => void;

  constructor(private alertService: AlertService, changeDetectorRef: ChangeDetectorRef, media: MediaMatcher,
    private authService: AuthService) {
    if (this.authService.currentUserValue.rol != "admin") {
      this.authService.logout();
    }
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);

  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  shouldRun = true;
  ngOnInit() {

  }
  ngOnSubmit() {
  }

  alertDni() {
    //this.alertService.error("Formato de DNI incorrecto. El DNI debe de tener 8 números y sin letra", false);
  }

}
