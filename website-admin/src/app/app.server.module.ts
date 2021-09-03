import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    AppModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ServerModule,
  ],
  bootstrap: [AppComponent],
})
export class AppServerModule {}
