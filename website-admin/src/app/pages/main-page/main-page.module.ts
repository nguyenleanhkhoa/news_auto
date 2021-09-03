import { DialogCategoryComponent } from './category-page/component/dialog-category/dialog-category.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FooterComponent } from './../../component/footer/footer.component';
import { SideBarComponent } from './../../component/side-bar/side-bar.component';
import { HeaderBarComponent } from './../../component/header-bar/header-bar.component';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './main-page.component';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { MainRoutingModule } from './main-routing.module';
import { FormsModule } from '@angular/forms';
import { NewsApprovedComponent } from './news-page/news-approved/news-approved.component';
import { NewsPageComponent } from './news-page/news-page.component';
import { MaterialModule } from 'src/app/shared/material/material.module';
import { NewsNotapprovedComponent } from './news-page/news-notapproved/news-notapproved.component';
import { WebsitePageComponent } from './website-page/website-page.component';
import { CategoryPageComponent } from './category-page/category-page.component';
import { AddNewsPageComponent } from './news-page/add-news-page/add-news-page.component';
import { EditNewsPageComponent } from './news-page/edit-news-page/edit-news-page.component';
import { AddWebsitePageComponent } from './website-page/add-website-page/add-website-page.component';
import { DialogAddNewUserComponent } from 'src/app/component/dialog/dialog-add-new-user/dialog-add-new-user.component';
import { StoreModule } from '@ngrx/store';
import { NgxPaginationModule } from 'ngx-pagination';
import { categoryReducer } from './category-page/ngrx/reducers/ category.reducer';
import { HttpInterceptorService } from 'src/app/services/http-interceptor.service';

@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    MainPageComponent,
    HeaderBarComponent,
    SideBarComponent,
    FooterComponent,
    NewsApprovedComponent,
    NewsNotapprovedComponent,
    WebsitePageComponent,
    CategoryPageComponent,
    NewsPageComponent,
    AddNewsPageComponent,
    EditNewsPageComponent,
    AddWebsitePageComponent,

    DialogAddNewUserComponent,
    CategoryPageComponent,
    DialogCategoryComponent
  ],
  exports: [MainPageComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true,
    },
  ],
  entryComponents: [
    DialogCategoryComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MainRoutingModule,
    MaterialModule,
    NgxPaginationModule,
    HttpClientModule,
    StoreModule.forRoot({ category: categoryReducer }),
  ],
})
export class MainPageModule { }
