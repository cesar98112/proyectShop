import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { error } from 'console';

import { catchError, Observable, throwError } from 'rxjs';


@Injectable()
export class AddTokenInterceptor implements HttpInterceptor {

  constructor( private router:Router){}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    
  const token = localStorage.getItem('token')
  
  if(token){
    console.log(token)
    const cloned = req.clone({setHeaders:{Authorization: `Bearer ${token}`}})
     return next.handle(cloned);
  }
  return next.handle(req).pipe(catchError((error:HttpErrorResponse)=>{
    console.log(error.status)
      if(error.status == 401){
        this.router.navigate(["/login"])
      }
      
       
    
    return throwError(()=> new Error('Error'))
  }))

    
  }
  
}

