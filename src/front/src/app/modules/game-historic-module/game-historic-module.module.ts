import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameHistoricModuleRoutingModule } from './game-historic-module-routing.module';
import { MainComponent } from './components/main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { AllGamesDialogComponent } from './components/all-games-dialog/all-games-dialog.component';


@NgModule({
  declarations: [
    MainComponent,
    AllGamesDialogComponent
  ],
  imports: [
    CommonModule,
    GameHistoricModuleRoutingModule,
    HttpClientModule
  ]
})
export class GameHistoricModuleModule { }
