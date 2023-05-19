import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Games } from "src/app/interfaces/palabra";

@Component({
  selector: "app-all-games-dialog",
  templateUrl: "./all-games-dialog.component.html",
  styleUrls: ["./all-games-dialog.component.scss"],
})
export class AllGamesDialogComponent {
  games: Games[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: AllGamesDialogComponent) {}

  ngOnInit() {
    this.games = this.data.games;
  }
}
