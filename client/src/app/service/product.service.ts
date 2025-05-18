import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../interfaces/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor( private http:HttpClient) { }

  getProduct():Observable<Product[]>{
    const token = localStorage.getItem("token")
    console.log(token)
  
    return this.http.get<Product[]>("http://localhost:8080/api/product/getAll")
  }
}
