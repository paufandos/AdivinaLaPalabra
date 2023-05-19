import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

fdescribe('LoginComponent', () => {
  let login: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        BrowserModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatDialogModule,
        FormsModule,
        ReactiveFormsModule,
      ],
      declarations: [LoginComponent],
    })
      .compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(LoginComponent);
        login = fixture.componentInstance;
        fixture.detectChanges();
      });
  });

  it('Se debería crear', () => {
    expect(login).toBeTruthy();
  });

  it('Debe retornar formulario invalido', () => {
    const username = login.userForm.controls['username'];
    username.setValue('pepe');
    expect(login.userForm.invalid).toBeTrue();
  });

  it('Debe retornar formulario valido', () => {
    const username = login.userForm.controls['username'];
    username.setValue('pepe');
    const password = login.userForm.controls['password'];
    password.setValue('123456');

    expect(login.userForm.invalid).toBeFalse();
  });

  it('Debe retornar mensaje de error para required', () => {
    const error = { errors: { required: true } };
    const result = login.identifyError(error);
    expect(result).toEqual('El campo es requerido');
  });

  it('Debe retornar mensaje de error para pattern', () => {
    const error = { errors: { pattern: true } };
    const result = login.identifyError(error);
    expect(result).toEqual('No puede contener caracteres especiales');
  });

  it('Debe retornar mensaje de error para minlength', () => {
    const error = { errors: { minlength: { requiredLength: 5 } } };
    const result = login.identifyError(error);
    expect(result).toEqual('Debe de tener al menos 5 caracteres');
  });

  it('Debe retornar mensaje de error para maxlength', () => {
    const error = { errors: { maxlength: { requiredLength: 5 } } };
    const result = login.identifyError(error);
    expect(result).toEqual('No debe superar 5 caracteres');
  });
});
