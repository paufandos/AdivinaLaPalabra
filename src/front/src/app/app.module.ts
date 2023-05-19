import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DialogComponent } from './components/dialog/dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MainInterceptor } from './interceptors/main.interceptor';
import { DialogFinishComponent } from './components/dialog-finish/dialog-finish.component';
import { Logininterceptor } from './interceptors/login.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { LoginComponent } from './components/login/login.component';
import { AllGamesDialogComponent } from './components/all-games-dialog/all-games-dialog.component';
import { GameHistoryComponent } from './components/game-history/game.history.component';
import { MainComponent } from './components/main/main.component';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    DialogComponent,
    DialogFinishComponent,
    LoginComponent,
    AllGamesDialogComponent,
    GameHistoryComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule, 
    CommonModule,
    HttpClientModule,
    FormsModule,
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
