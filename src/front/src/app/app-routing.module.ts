import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'main',
    loadChildren: () =>
      import('./modules/main-module/main-module.module').then(
        (module) => module.MainModuleModule
      ),
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'game-historic',
    loadChildren: () =>
      import('./modules/game-historic-module/game-historic-module.module').then(
        (module) => module.GameHistoricModuleModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
