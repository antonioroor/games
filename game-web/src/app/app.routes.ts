import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'juegos',
    loadComponent: () => import('./modules/games/pages/games/games.component').then(c => c.GamesComponent)
  },
  {
    path: 'editar-juego/:id',
    loadComponent: () => import('./modules/games/pages/game-form/game-form.component').then(c => c.GameFormComponent)
  },
  {
    path: 'nuevo-juego',
    loadComponent: () => import('./modules/games/pages/game-form/game-form.component').then(c => c.GameFormComponent)
  },
  {
    path: '',
    redirectTo: '/juegos',
    pathMatch: 'full'
  }
];
