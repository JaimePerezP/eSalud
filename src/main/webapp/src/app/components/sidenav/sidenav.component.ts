import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { AuthService } from '../../_services'
import { Router } from '@angular/router';


@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {
  router: Router;
  authService: AuthService;
  mobileQuery: MediaQueryList;
  rol = this.readLocalStorageValue('currentUser');

  fillerNav= [
      { name: "Citas", route: "citas", icon: "assignment" },
      { name: "Registrar Cita", route: "RegistrarCita", icon: "edit" },
      { name: "Cambiar a modo medico", route: "CambioMedico", icon: "supervisor_account" },
      { name: "Salir", route: "/", icon: "logout" }
  ]

  fillerNav2=[
    { name: "Citas", route: "citas", icon: "assignment" },
    { name: "Registrar Cita", route: "RegistrarCita", icon: "edit" },
    { name: "Salir", route: "/", icon: "logout" }
  ]

  fillerNav3= [
    { name: "Citas", route: "citas", icon: "assignment" },
    { name: "Registrar Cita", route: "RegistrarCita", icon: "edit" },
    { name: "Cambiar a modo gestor", route: "CambioGestor", icon: "supervisor_account" },
    { name: "Salir", route: "/", icon: "logout" }
  ]

  fillerContent = Array.from({ length: 50 }, () =>
    `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
       labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
       laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
       voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
       cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.`);

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, authService: AuthService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
    this.authService = authService;
  }
  readLocalStorageValue(key: string): string {
    var item = JSON.parse(localStorage.getItem(key));
    var role = item.rol;
    return role;
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  shouldRun = true;
  ngOnInit() {
  }

  desconectar() {
    this.authService.logout();
  }
}
