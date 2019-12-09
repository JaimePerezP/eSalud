import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AlertService } from 'src/app/_services/alert.service';

import { UserService } from '../../_services';

@Component({
    selector: 'app-RegistrarPaciente',
    templateUrl: './RegistrarPaciente.component.html',
    styleUrls: ['./RegistrarPaciente.component.css']
})
export class RegistrarPacienteComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private userService: UserService,
        private alertService: AlertService
    ) { }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            dni: ['', Validators.required],
            nombre: ['', Validators.required],
            apellidos: ['', Validators.required],
            centro: ['', Validators.required],
            tel: ['', Validators.required],
            correo: ['', Validators.required],
            password: ['', Validators.required],
            localidad: '',
            rol: 'paciente',
            medico: '',
        });
    }

    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;
        this.alertService.clear();

        if (this.registerForm.invalid) {
            return;
        }
        if ((this.f.dni.value.length != 8)) {
            //this.sidenavAdmin.alertDni();
            this.alertService.error("Formato de DNI incorrecto. El DNI debe de tener 8 números y sin letra", false);
            return;
        }
        if (!allLetter(this.f.nombre)) {
            this.alertService.error("Formato de nombre incorrecto.", false);
            return;
        }
        if (!allLetter(this.f.apellidos)) {
            this.alertService.error("Formato de apellidos incorrecto.", false);
            return;
        }
        if (this.f.password.value.length < 5 || (checkPass(this.f.password) == false)) {
            this.alertService.error("Formato de contraseña incorrecta. La contraseña debe contener al menos 6 carácteres, mayúsuculas y minúsculas, números y algún símbolo.", false);
            return;
        }
        if (!allLetter(this.f.centro)) {
            this.alertService.error("Formato del centro incorrecto.", false);
            return;
        }

        if ((this.f.tel.value.length != 9)) {
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





        this.loading = true;
        this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.alertService.success('Registro completado', true);
                    console.log("[CLIENTE] Médico registrado.");
                },
                error => {
                    this.alertService.error('Error: El médico ya está registrado.');
                    this.loading = false;
                });

        function allLetter(inputtxt) {
            var letters = /^[A-Za-z]+$/;
            var space = ' ';
            if (inputtxt.value.match(letters) || inputtxt.value.match(space)) {
                return true;
            }
            else {
                return false;
            }
        }

        function checkPass(inputText) {
            var all = /^[A-Za-z0-9,!,@,#,$,%,^,&,*,?,_,~]+$/;

            if (inputText.value.match(all)) {
                return true;
            } else {
                return false;
            }

        }
    }
}