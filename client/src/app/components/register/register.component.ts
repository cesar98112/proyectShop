import { Component } from '@angular/core';
import { User } from '../../interfaces/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  username:string=""
  password:string=""
  repeatPassword:string=""
  constructor(private service:UserService){

  }
  addUser(){
    if(this.repeatPassword != this.password){
      alert("las contraseñas no coinciden")
      return
    }

    const user :User ={
      username: this.username,
      password:this.password,
      
    }

    this.service.sigIn(user).subscribe(data =>{
      console.log(data.token)
    })
  }
}
