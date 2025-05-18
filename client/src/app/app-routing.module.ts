import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ErrorComponent } from './components/error/error.component';
import { ProductComponent } from './components/product/product.component';

const routes: Routes = [{
  path:"login", component:LoginComponent

},
{
  path:"product", component:ProductComponent
},
{
  path:"register", component:RegisterComponent
},
{
path:"error", component:ErrorComponent
},
{
 path:"" , component:LoginComponent
},
{
  path:"**",redirectTo:"/error", pathMatch:"full"
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
