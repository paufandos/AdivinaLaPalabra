import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MainModuleModule } from './modules/main-module/main-module.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DialogComponent } from './components/dialog/dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MainInterceptor } from './interceptores/main.interceptor';
import { DialogFinishComponent } from './components/dialog-finish/dialog-finish.component';
import { Logininterceptor } from './interceptores/login.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { LoginComponent } from './components/login/login.component';
import { GameHistoricModuleModule } from './modules/game-historic-module/game-historic-module.module';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    DialogComponent,
    DialogFinishComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MainModuleModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    GameHistoricModuleModule
  ],
  providers: [CookieService,
    {
    provide: HTTP_INTERCEPTORS,
    useClass: MainInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: Logininterceptor,
    multi: true,
  },
],

  bootstrap: [AppComponent]
})
export class AppModule {}
