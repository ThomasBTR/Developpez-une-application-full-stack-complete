import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ArticlesComponent} from "./components/article/articles.component";
import {DetailComponent} from "./components/detail/detail.component";

const routes: Routes = [
  {path: '', title: 'Your Feed', component: ArticlesComponent},
  {path: 'detail', title: 'Article Detail', component: DetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule {
}
