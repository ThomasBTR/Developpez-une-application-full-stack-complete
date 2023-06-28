import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListComponent} from "./components/list/list.component";
import {DetailComponent} from "./components/detail/detail.component";

const routes: Routes = [
  {path: '', title: 'Your Feed', component: ListComponent},
  {path: 'detail/:id', title: 'Article Detail', component: DetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule {
}
