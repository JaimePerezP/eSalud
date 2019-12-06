import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarPacienteComponent } from './RegistrarPaciente.component';

describe('RegistrarMedicoComponent', () => {
  let component: RegistrarPacienteComponent;
  let fixture: ComponentFixture<RegistrarPacienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistrarPacienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
