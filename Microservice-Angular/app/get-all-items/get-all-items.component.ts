import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-get-all-items',
  templateUrl: './get-all-items.component.html',
  styleUrls: ['./get-all-items.component.css']
})
export class GetAllItemsComponent implements OnInit {

    constructor( private httpClient:HttpClient){}
  
  getAllItems(){
	  let items= []; 
	  this.httpClient.get('http://10.10.11.70:8084/items/items')
		.subscribe(
		(data:any[]) =>{
			this.items=data;
			console.log(this.items);

		}
		)
	  }

	  
	  ngOnInit() {
	this.getAllItems();
  }
  }


