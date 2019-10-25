import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './_services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'eSalud';

  currentUser: any;

  constructor(
      private router: Router,
      private authService: AuthService
  ) {
      this.authService.currentUser.subscribe(x => this.currentUser = x);
  }

}
