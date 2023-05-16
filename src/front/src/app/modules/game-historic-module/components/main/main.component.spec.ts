import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainComponent } from './main.component';

import { CommonModule } from '@angular/common';

import { GameHistoricModuleRoutingModule } from '../../game-historic-module-routing.module';

import { Games } from 'src/app/interfaces/palabra';

import { HttpClientModule } from '@angular/common/http';

fdescribe('Game-historic', () => {
  let component: MainComponent;

  let fixture: ComponentFixture<MainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CommonModule,

        GameHistoricModuleRoutingModule,

        HttpClientModule,
      ],

      declarations: [MainComponent],
    })

      .compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(MainComponent);

        component = fixture.componentInstance;

        fixture.detectChanges();
      });
  });

  it('Se deberia crear', () => {
    expect(component).toBeTruthy();
  });

  it('Debe convertir una fecha de tipo String a un Date', () => {
    const Game: Games = {
      date: '10-09-2001 08:00',

      winned: false,

      attempts: 0,
      correctWord: false
    };

    const Games: Games[] = component.lastTenGames;

    Games.push(Game);

    component.convertDate(Games);

    expect(Games[0].date).toEqual('09/10/2001 08:00');
  });
});
