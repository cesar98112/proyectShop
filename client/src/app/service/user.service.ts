import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  sigIn(user:User): Observable<any>{
    return this.http.post("http://localhost:8080/api/autent/createUser",user)
  }

  login(user:User):Observable<any>{
    return this.http.post("http://localhost:8080/api/autent/login",{username: user.username, password:user.password}, {responseType:"text"})
  }
}
