import { Component, OnInit } from '@angular/core';
import {
  LetterStatus,
  Palabra,
  Rounds,
  DataDialog,
} from '../../interfaces/palabra';
import { GameService } from 'src/app/services/game.service';
import { TECLADO } from 'src/assets/datos/data';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from 'src/app/components/dialog/dialog.component';
import { DialogFinishComponent } from 'src/app/components/dialog-finish/dialog-finish.component';
import { LOST_GAME_DIALOG, WIN_GAME_DIALOG } from 'src/assets/datos/consts';

@Component({
  selector: 'app-main',

  templateUrl: './main.component.html',

  styleUrls: ['./main.component.scss'],
})
export class MainComponent implements OnInit {
  letterStatus: LetterStatus[] = [];

  positionInput = 0;

  wordSend: Palabra = {
    pos0: '',
    pos1: '',
    pos2: '',
    pos3: '',
    pos4: '',
  };

  positionSelec: number = 0;

  contRound = 0;

  disableKeyboard: boolean = false;

  teclado: string[] = TECLADO;

  tecladoStatus: string[] = [];

  isDelete = true;

  emptyLetter = '';

  tries: boolean = true;

  winValue = true;

  round: Rounds = {
    wordRound: ['', '', '', '', ''],
    wordStatusRound: [],
    positionInput: 0,
  };

  rounds: Rounds[] = [this.round];

  positionRoundLetter: number = 0;

  word = this.round.wordRound;

  constructor(private gameService: GameService, private dialog: MatDialog) {}

  ngOnInit() {
    this.gameService.newGame();
    this.gameService.$disableKeyboard.subscribe({
      next: (response: boolean) => {
        this.disableKeyboard = response;
      },
    });
    this.gameService.$disableKeyboard.next(false);
  }

  sendWord() {
    this.gameService.getWordIfExist(this.word.join('')).subscribe({
      next: (response: any) => {
        if (!response.wordExists) {
          this.openDialogNoExist();
          return;
        }
        this.validatePosition();
      },
    });
  }

  writeLetter(tecla: string) {
    if (this.findCorrectIndex() === -1) return;
    this.rounds[this.contRound].wordRound[
      this.rounds[this.contRound].positionInput
    ] = tecla;
    this.rounds[this.contRound].positionInput = this.findCorrectIndex();
  }

  deleteLetter() {
    this.changePositionWhenDelete();
    this.word[this.rounds[this.contRound].positionInput] = '';
  }

  getPosition(idCasilla: number, idRound: number) {
    if (idRound !== this.contRound) return;
    this.rounds[this.contRound].positionInput = idCasilla;
    this.positionRoundLetter = this.rounds[this.contRound].positionInput;
  }

  private openDialogNoExist() {
    this.dialog.open(DialogComponent, {
      data: { text: 'La palabra no existe', createButton: true },
    });
  }

  private validatePosition() {
    this.setValuesWord();
    this.gameService.getValidatePosition(this.wordSend).subscribe({
      next: (response: LetterStatus[]) => {
        this.letterStatus = response;
        this.setStatus();
        this.setTecladoStatus();
        this.checkWin();
      },
    });
  }

  private setStatus() {
    this.letterStatus.forEach((value, index) => {
      this.rounds[this.contRound].wordStatusRound[index] = value.status;
    });
  }

  private setTecladoStatus() {
    this.letterStatus.forEach((posicion) => {
      const index = this.teclado.findIndex((value) => {
        return value.toLocaleLowerCase() === posicion.letter;
      });
      if (this.tecladoStatus[index] !== 'MATCHED') {
        this.tecladoStatus[index] = posicion.status;
      }
    });
  }

  private findCorrectIndex() {
    return this.rounds[this.contRound].wordRound.findIndex((value) => {
      return value === '';
    });
  }

  private changePositionWhenDelete() {
    const positionLetter = this.rounds[this.contRound].positionInput;
    if (
      this.word[positionLetter] !== '' &&
      this.word[positionLetter] !== undefined
    ) {
      return;
    }
    if (positionLetter > this.word.length - 1 || positionLetter < 0) {
      this.rounds[this.contRound].positionInput = this.word.length - 1;

      return;
    }
    if (positionLetter > 0) {
      this.rounds[this.contRound].positionInput--;

      return;
    }
  }

  private setValuesWord() {
    Object.keys(this.wordSend).forEach((key, index) => {
      this.wordSend[key as keyof Palabra] =
        this.rounds[this.contRound].wordRound[index];
    });
  }

  private checkWin() {
    this.winValue = true;
    this.rounds[this.contRound].wordStatusRound.forEach((value) => {
      if (value != 'MATCHED') this.winValue = false;
    });
    if (this.winValue) {
      this.decideWinorLost('');
      return;
    }
    this.checkTries();
  }

  private checkTries() {
    this.gameService.getAttempts().subscribe({
      next: (response: any) => {
        if (!response.canMoreAttempts) {
          this.gameLost();
          return;
        }
        this.newRound();
        this.contRound++;
      },
    });
  }

  private gameLost() {
    let correctWord = '';
    this.gameService.getCorrectWord().subscribe({
      next: (response: any) => {
        correctWord = response.correctWord;
        this.decideWinorLost(correctWord);
      },
    });
  }

  private decideWinorLost(correctword: string) {
    let dialogInfo: DataDialog = WIN_GAME_DIALOG;

    if (!this.winValue) {
      dialogInfo = LOST_GAME_DIALOG;
      dialogInfo.correctWord = correctword;
    }

    this.dialog.open(DialogFinishComponent, { data: dialogInfo });

    this.disableKeyboardChange();
  }

  private disableKeyboardChange() {
    this.gameService.$disableKeyboard.next(true);
  }

  private newRound() {
    this.word = ['', '', '', '', ''];
    let wordStatus = ['', '', '', '', ''];
    let newRound: Rounds = {
      wordRound: this.word,
      wordStatusRound: wordStatus,
      positionInput: 0,
    };
    this.rounds.push(newRound);
  }
}
