import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../service/product.service';

@Component({
  selector: 'app-product',
  standalone: false,
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {

  constructor(
    private service: ProductService,
    private route:Router
  ){

  }
  ngOnInit(): void {
    this.service.getProduct().subscribe(data =>{
      console.log(data)
    })
  }
  clear(){
    localStorage.setItem("token","")
    this.route.navigate(["/login"]);
  }
}
