import { Component, OnInit } from '@angular/core';
import { TestService } from '../../service/test.service';
import { UserService } from '../../service/user.service';
import { User } from '../../interfaces/user';
import { Router } from '@angular/router';
import { ProductService } from '../../service/product.service';


@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{
   
   username:string =''
   password:string=''
   constructor( 
    private router:Router,
    private service:UserService
  ){

   }
  

   login(){

    const user:User={
      username:this.username,
      password:this.password
    }
    
    this.service.login(user).subscribe(data =>{
      localStorage.setItem("token",data)
      
      this.router.navigate(["/product"])
    })
   }
}
