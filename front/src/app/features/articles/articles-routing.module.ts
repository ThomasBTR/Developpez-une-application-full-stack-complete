import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ArticlesComponent} from "./components/article/articles.component";

const routes: Routes = [
  {path: '', title: 'Your Feed', component: ArticlesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule {
}
