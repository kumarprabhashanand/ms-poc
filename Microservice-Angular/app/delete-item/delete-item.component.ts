import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-delete-item',
  templateUrl: './delete-item.component.html',
  styleUrls: ['./delete-item.component.css']
})
export class DeleteItemComponent implements OnInit {

  constructor( private httpClient:HttpClient){}
  id:number="";
  private headers= new Headers({'Content-Type': 'application/json'});
  onNameKeyUp(event:any){
	  this.id=event.target.value;
  }
  
  DeleteItem(){
	  console.log(this.id);
	   let items= []; 
		const url=("http://10.10.11.70:8084/items/items/")+this.id;
	  return this.httpClient.delete(url,{headers:this.headers}).toPromise()
	  .then(()=>{
	  
	  this.fetchData();
	  
	  })
		
		}
		 
	  
  ngOnInit() {
  }

}
