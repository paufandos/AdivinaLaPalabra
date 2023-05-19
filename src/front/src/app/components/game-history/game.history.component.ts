import { DatePipe } from "@angular/common";
import { Component, ElementRef } from "@angular/core";
import { Games, LastTenGames } from "src/app/interfaces/palabra";
import { GameService } from "src/app/services/game.service";
import { AllGamesDialogComponent } from "../all-games-dialog/all-games-dialog.component";
import { MatDialog } from "@angular/material/dialog";

@Component({
  selector: "app-main",
  templateUrl: "./game.history.component.html",
  styleUrls: ["./game.history.component.scss"],
})
export class GameHistoryComponent {
  allGames: Games[] = [];
  lastTenGames: Games[] = [];
  top3Games: Games[] = [];
  areEnoughGames!: boolean;

  constructor(private gameService: GameService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.setGamesHistory();
  }

  setGamesHistory(): void {
    this.gameService.getGamesHistory().subscribe((response) => {
      this.setTenGames(response[0]);
      this.setTop3Games(response[1]);
    });
  }

  setTenGames(gamesResponse: LastTenGames) {
    this.lastTenGames = gamesResponse.games;
    this.areEnoughGames = gamesResponse.hasEnoughGames;
    this.convertDate(this.lastTenGames);
  }

  setTop3Games(gamesResponse: Games[]) {
    this.top3Games = gamesResponse;
    this.convertDate(this.top3Games);
  }

  convertDate(games: Games[]) {
    const datePipe = new DatePipe("en-US");
    games.forEach((item, index) => {
      const fecha: Date | null = new Date(item.date);
      games[index].date = datePipe.transform(fecha, "dd/MM/yyyy HH:mm") ?? "";
    });
  }

  showAllGames() {
    this.gameService.getAllGames().subscribe({
      next: (response) => {
        this.allGames = response;
        this.convertDate(this.allGames);
        this.dialog.open(AllGamesDialogComponent, {
          data: {
            games: this.allGames,
          },
        });
      },
    });
  }
}
