import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Games } from 'src/app/interfaces/palabra';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent {
  Games: Games[] = [];

  watchAllGames = false;

  areEnoughGames = true;

  top3Games: Games[] = [];

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.setAllGames();
    this.setTop3Games();
  }

  getLastGames(): Games[] {
    return this.Games;
  }

  setTenGames() {
    this.gameService.getTenGames().subscribe((response: Games[]) => {
      this.Games = response;
      this.convertDate();
    });
  }

  setAllGames() {
    this.gameService.getAllGames().subscribe({
      next: (response) => {
        this.Games = response;
      },
      error: () => {
        this.setTenGames();
        this.areEnoughGames = false;
      },
      complete: () => {this.convertDate()}
    });
  }

  setTop3Games() {
    this.gameService.getTop3Games().subscribe({
      next: (response) => {
        this.top3Games = response;
        this.convertDate()
      },
    });
  }

  convertDate() {
    const datePipe = new DatePipe('en-US');
    this.Games.forEach((item, index) => {
      const fecha: Date | null = new Date(item.date);
      this.Games[index].date =
        datePipe.transform(fecha, 'dd/MM/yyyy HH:mm') ?? '';
    });
  }

  showAllGames() {
    this.watchAllGames = true;
  }

  showTenGames() {
    this.watchAllGames = false;
  }
}
