import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { DialogComponent } from '../components/dialog/dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { GameService } from '../services/game.service';
import { Router } from '@angular/router';
import { baseUrl } from 'src/assets/datos/consts';

@Injectable()
export class MainInterceptor implements HttpInterceptor {
  private errorMessageDefault =
    'Ups, ha habido un error de conexión, por favor inténtelo de nuevo mas tarde';

  errorNoConection = 0;
  errorTokenExpired = 401;
  errorInsufficientGames = 406;
  urlLogin= baseUrl + "auth/login";
  urlGames= baseUrl + "getAllGames";

  constructor(
    private router: Router,
    private dialog: MatDialog,
    private gameService: GameService
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error) => {
        return throwError(this.messageError(error));
      })
    );
  }

  messageError(error: any) {
    let message = this.errorMessageDefault;
    if (error.status === this.errorTokenExpired && error.url !== this.urlLogin) {
      console.log(error.url)
      message = 'Debe iniciar sesión para continuar.';
      this.showError(message);
      this.router.navigateByUrl('login');
    }
    if (error.status !== this.errorNoConection && error.status !== this.errorInsufficientGames) {
      message = error.error.message;
      this.showError(message);
    }
    this.gameService.$disableKeyboard.next(true);
  }
  showError(message: string) {
    this.dialog.open(DialogComponent, {
      data: {
        text: message,
        createButton: true,
      },
    });
  }
}
