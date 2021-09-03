import { WebsitePageComponent } from './website-page/website-page.component';
import { MainPageComponent } from './main-page.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewsPageComponent } from './news-page/news-page.component';
import { CategoryPageComponent } from './category-page/category-page.component';


const routes: Routes = [
  {
    path: '',
    component: MainPageComponent,
    children: [
      {
        path: 'news',
        component: NewsPageComponent,
      },
      {
        path: 'category',
        component: CategoryPageComponent
      },
      {
        path: 'website',
        component: WebsitePageComponent,
      }

      // {
      //   path: 'config-news',
      //   component: ConfigNewsPageComponent
      // },

    ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  // imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
