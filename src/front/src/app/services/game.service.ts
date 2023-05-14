import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Injectable } from '@angular/core';

import { BehaviorSubject, Observable } from 'rxjs';
import {
  Games,
  GameID,
  LetterStatus,
  Palabra,
} from '../interfaces/palabra';

import { baseUrl } from 'src/assets/datos/consts';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  wordExist: any;

  valueListTenGames = {
    date: 'string',

    winned: false,

    attempts: 0,
  };

  id: string = '';

  constructor(private http: HttpClient) {}

  $disableKeyboard: BehaviorSubject<boolean> = new BehaviorSubject(false);

  getWordIfExist(wordInsert: string): Observable<boolean> {
    return this.http.get<boolean>(
      baseUrl.concat('checkIfWordExists/' + wordInsert)
    );
  }

  getAttempts(): Observable<boolean> {
    return this.http.get<boolean>(
      baseUrl.concat('checkAttemptsInRange/' + this.id)
    );
  }

  getCorrectWord(): Observable<string> {
    return this.http.get<string>(baseUrl.concat('getCorrectWord/' + this.id));
  }

  newGame() {
    this.http.get<GameID>(baseUrl.concat('newGame')).subscribe({
      next: (response: GameID) => {
        this.id = response.game_id;
      },
    });
  }

  getValidatePosition(wordInsert: Palabra): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
    });
    return this.http.post<LetterStatus>(
      baseUrl.concat('validatePositions/' + this.id),
      wordInsert,
      { headers }
    );
  }

  getTenGames(): Observable<Games[]> {
    return this.http.get<Games[]>(
      baseUrl.concat('getLastTenGames')
    );
  }

  getAllGames(): Observable<Games[]> {
    return this.http.get<Games[]>(
      baseUrl.concat('getAllGames')
    );
  }
}
