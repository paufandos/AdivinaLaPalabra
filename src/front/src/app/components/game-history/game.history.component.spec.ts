import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GameHistoryComponent } from './game.history.component';
import { CommonModule } from '@angular/common';
import { Games } from 'src/app/interfaces/palabra';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';

fdescribe('Game-historic', () => {
  let component: GameHistoryComponent;

  let fixture: ComponentFixture<GameHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CommonModule,
        HttpClientModule,
        MatDialogModule
      ],

      declarations: [GameHistoryComponent],
    })

      .compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(GameHistoryComponent);

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
