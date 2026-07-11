import { Routes } from '@angular/router';
import { MainLayout } from './layout/main-layout/main-layout';
import { Dashboard } from './pages/dashboard/dashboard';
import { Pacientes } from './pages/pacientes/pacientes';

export const routes: Routes = [
  {
    path: '',
    component: MainLayout,
    children: [
      {
        path: '',
        component: Dashboard
      },
      {
        path: 'pacientes',
        component: Pacientes
      }
    ]
  },
  {
    path: '**',
    redirectTo: ''
  }
];