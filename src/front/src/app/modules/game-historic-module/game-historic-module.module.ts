import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameHistoricModuleRoutingModule } from './game-historic-module-routing.module';
import { MainComponent } from './components/main/main.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    MainComponent
  ],
  imports: [
    CommonModule,
    GameHistoricModuleRoutingModule,
    HttpClientModule
  ]
})
export class GameHistoricModuleModule { }
