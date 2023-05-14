import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { StorageService } from '../services/storage.service';

@Injectable()
export class Logininterceptor implements HttpInterceptor {
  emptyToken = "";

  constructor(private router: Router, private storageService: StorageService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.storageService.getToken();

    if (token === this.emptyToken) {
      this.router.navigateByUrl('login');
      return next.handle(req);;
    }

    const TokenReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    return next.handle(TokenReq);
  }
}
