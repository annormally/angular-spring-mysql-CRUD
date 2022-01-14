import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListComponent} from "./components/list/list.component";
import {DetailsComponent} from "./components/details/details.component";
import {AddComponent} from "./components/add/add.component";

const routes: Routes = [
  {path: '', redirectTo: 'tutorials', pathMatch: 'full'},
  {path: 'tutorials', component: ListComponent},
  {path: 'tutorials/:id', component: DetailsComponent},
  {path: 'add', component: AddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
