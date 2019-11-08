import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthService } from '../_services';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authService: AuthService
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const currentUser = this.authService.currentUserValue;
        if (currentUser) {
            // autorizado, por tanto devolvemos true
            return true;
        }

        // no está logueado por tanto redirigimos a la página de login
        this.router.navigate(['/auth/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }
}