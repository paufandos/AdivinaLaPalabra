import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainComponent } from './main.component';

import { CommonModule } from '@angular/common';

import { HttpClientModule } from '@angular/common/http';

import { MainModuleRoutingModule } from '../../main-module-routing.module';

import { FormsModule } from '@angular/forms';

import { MatDialogModule } from '@angular/material/dialog';

fdescribe('MainComponent', () => {
  let component: MainComponent;

  let fixture: ComponentFixture<MainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CommonModule,

        HttpClientModule,

        MainModuleRoutingModule,

        FormsModule,

        MatDialogModule,
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

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Debe de asignar un valor en el array de wordRound', () => {
    const contRound = component.contRound;

    const rounds = component.rounds;

    const positionInput = 0;

    const round = component.round;

    rounds.push(round);

    component.writeLetter('a');

    expect(rounds[contRound].wordRound[positionInput]).toEqual('a');
  });

  it('Debe borrar un valor en el array de wordRound', () => {
    const contRound = component.contRound;

    const rounds = component.rounds;

    const positionInput = 0;

    const round = component.round;

    rounds.push(round);

    rounds[contRound].wordRound[positionInput] = 'a';

    component.deleteLetter();

    expect(rounds[contRound].wordRound[positionInput]).toEqual('');
  });

  it('Debe asignar un valor numerico a positionInput', () => {
    const contRound = component.contRound;

    const rounds = component.rounds;

    const round = component.round;

    rounds.push(round);

    component.getPosition(0, 0);

    expect(rounds[contRound].positionInput).toEqual(0);
  });
});
