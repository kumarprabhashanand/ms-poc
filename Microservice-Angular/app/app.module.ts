import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { GetAllItemsComponent } from './get-all-items/get-all-items.component';
import { RouterModule } from '@angular/router';
import { GetItemComponent } from './get-item/get-item.component';
import { DeleteItemComponent } from './delete-item/delete-item.component';


@NgModule({
  declarations: [
    AppComponent,
    GetAllItemsComponent,
    GetItemComponent,
    DeleteItemComponent
  ],
  imports: [
    BrowserModule,
	HttpClientModule,
	RouterModule.forRoot([
	
	{
		path:'getAllItems',
		component:GetAllItemsComponent
	}
	
		{
		path:'deleteItem',
		component:DeleteItemComponent
	}
	
	])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
