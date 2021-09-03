import { MainPageComponent } from './pages/main-page/main-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadingStrategy, PreloadAllModules } from '@angular/router';
import { CategoryPageComponent } from './pages/main-page/category-page/category-page.component';


const routes: Routes = [

  {
    path: 'login',
    component: LoginPageComponent,
  },
  {
    path: 'overview',
    loadChildren: () => import('../app/pages/main-page/main-page.module').then(m => m.MainPageModule),

  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [

    RouterModule.forRoot(routes, { enableTracing: false, initialNavigation: 'enabled' })],
  // imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
