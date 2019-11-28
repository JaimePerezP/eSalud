import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { UserService, AuthService } from '../../../_services';
import { AlertService } from 'src/app/_services/alert.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authService: AuthService,
        private userService: UserService,
        private alertService: AlertService
    ) {
        // redirige al home si el usuario ya está logueado
        if (this.authService.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            dni: ['', Validators.required],
            password: ['', [Validators.required]],
            password2: ['', [Validators.required]],
            correo: ['', Validators.required],
            localidad: ['', Validators.required],
            nombre: ['', Validators.required],
            apellidos: ['', Validators.required],
            tel: ['', Validators.required],
            centro: 'Hospital General Ciudad Real',
            rol: 'paciente'
        });
    }

    // getter para acceder a los controles del form
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;
        this.alertService.clear();


        // para aquí si el form es inválido
        if (this.registerForm.invalid) {
            return;
        }

        if ((this.f.dni.value.length != 8)) {
            this.alertService.error("Formato de DNI incorrecto. El DNI debe de tener 8 números y sin letra", false);
            return;
        }

        if ((this.f.password.value.length < 5) || (checkPass(this.f.password) == false)) {
            this.alertService.error("Formato de contraseña incorrecta. La contraseña debe contener al menos 6 carácteres, al menos unamayúsucula, un número y un símbolo.", false);
            return;
        }
        if (this.f.password.value != this.f.password2.value) {
            this.alertService.error("Las contraseñas no coinciden.", false);
            return;
        }
        if (!allLetter(this.f.nombre)) {
            this.alertService.error("Formato de nombre incorrecto. Las tildes no son necesarias.", false);
            return;
        }
        if (!allLetter(this.f.apellidos)) {
            this.alertService.error("Formato de apellidos incorrecto. Las tildes no son necesarias.", false);
            return;
        }

        if ((this.f.tel.value.length != 9) && (!isNaN(this.f.tel.value))) {
            this.alertService.error("Formato de número de teléfono incorrecto. El teléfono debe tener al menos 9 números", false);
            return;
        }

        if (isNaN(this.f.tel.value)) {
            this.alertService.error("Formato de número de teléfono incorrecto. El teléfono debe ser un número", false);
            return;
        }

        if (!(
            (this.f.correo.value.includes('@')) &&
            (this.f.correo.value.includes('.es') || this.f.correo.value.includes('.com')))
        ) {
            this.alertService.error("Formato incorrecto del correo electrónico. ", false);
            return;
        }
        if (!allLetter(this.f.localidad)) {
            this.alertService.error("Formato de localidad incorrecto.", false);
        }

        this.loading = true;
        this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/auth/login'], { queryParams: { registered: true } });
                },
                error => {
                    this.alertService.error("Error: El usuario ya está registrado.");
                    this.loading = false;
                });

        function allLetter(inputtxt) {
            var letters = /^[A-Za-z]+$/;
            var space = ' ';
            if (inputtxt.value.match(letters) || inputtxt.value.match(space) || inputtxt.value.match("ñ") || inputtxt.value.match("Ñ")) {
                return true;
            }
            else {
                return false;
            }
        }

        function checkPass(inputText) {
            var texto = inputText.value;
            var numeros="0123456789";
  	        var mayusculas="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
            var simbolo="<>ºª\!|@·#$%&¬/()=?¿¡/*-+,;:^.'`[]{}_";
              
            var hayNumero = false;
            var hayMayuscula = false;
            var haySimbolo = false;
            var valido = true;

            console.log(numeros.length);  
	       	for(var i=0; i<texto.length; i++){
                   for(var l=0; l<numeros.length; l++){
                        if (texto[i] == numeros[l]){
                            hayNumero = true;
                        }
                   }
	        }
			
	       	for(var j=0; j<texto.length; j++){
                for(var m=0; m<mayusculas.length; m++){
                     if (texto[j] == mayusculas[m]){
                         hayMayuscula = true;
                     }
                }
	        }
	       	for(var k=0; k<texto.length; k++){
                for(var n=0; n<simbolo.length; n++){
                    if (texto[k] == simbolo[n]){
                        haySimbolo = true;
                    }
               }
            }
            console.log(hayNumero +" " + haySimbolo + " " +hayMayuscula);
			if (!hayMayuscula || !haySimbolo || !hayNumero){
				valido = false;
            }
            return valido;     

        }

    }
}
