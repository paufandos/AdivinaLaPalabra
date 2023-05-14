import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { DialogFinishComponent } from './components/dialog-finish/dialog-finish.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { GameHistoricModuleModule } from './modules/game-historic-module/game-historic-module.module';
import { MainModuleModule } from './modules/main-module/main-module.module';

fdescribe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MainModuleModule,
        BrowserAnimationsModule,
        MatDialogModule,
        FormsModule,
        ReactiveFormsModule,
        GameHistoricModuleModule],
      declarations: [
        AppComponent,
        NavbarComponent,
        FooterComponent,
        DialogComponent,
        DialogFinishComponent,
        LoginComponent,
      ],
    }).compileComponents();
  });

  it('Se deberia crear', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`Deberia tener el titulo de 'AdivinaLaPalabra-Front'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('AdivinaLaPalabra-Front');
  });

});
