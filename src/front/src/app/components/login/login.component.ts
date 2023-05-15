import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/palabra';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  minUserNameLenght = 4;
  minPasswordLenght = 6;
  maxPasswordLenght = 12;
  messageErrorUser = '';
  messageErrorPassword = '';
  messageError = '';

  userForm: FormGroup = this.formBuilder.group({
    username: [
      null,
      [
        Validators.required,
        Validators.minLength(this.minUserNameLenght),
        Validators.pattern('[a-z A-Z]*'),
      ],
    ],
    password: [
      null,
      [
        Validators.required,
        Validators.minLength(this.minPasswordLenght),
        Validators.maxLength(this.maxPasswordLenght),
      ],
    ],
  });

  constructor(
    private authservice: AuthService,
    private formBuilder: FormBuilder,
    public router: Router,
    private storageService: StorageService
  ) {}

  login() {
    if (!this.userForm.valid) {
      return;
    }
    this.doLogin();
  }

  private doLogin() {
    let user: User = this.getLoginParams();

    this.authservice.login(user).subscribe({
      next: (response: any) => {
        this.storageService.setToken(response.token);
        this.router.navigateByUrl('/main');
      },
      error: () => {}
    });
  }

  private getLoginParams(): User {
    const username = this.userForm.value.username;
    let usernameEncrypt = this.encrypt(username);

    const password = this.userForm.value.password;
    let passwordEncrypt = this.encrypt(password);

    return { name: usernameEncrypt, password: passwordEncrypt };
  }

  private encrypt(word: string) {
    let byteword = new TextEncoder().encode(word);
    return btoa(String.fromCharCode(...new Uint8Array(byteword)));
  }

  identifyError(error: any): string | null {
    if (error.errors?.['required']) {
      return 'El campo es requerido';
    }
    if (error.errors?.['pattern']) {
      return 'No puede contener caracteres especiales';
    }
    if (error.errors?.['minlength']) {
      return (
        'Debe de tener al menos ' +
        error.errors?.['minlength'].requiredLength +
        ' caracteres'
      );
    }
    if (error.errors?.['maxlength']) {
      return (
        'No debe superar ' +
        error.errors?.['maxlength'].requiredLength +
        ' caracteres'
      );
    }
    return null;
  }
}
