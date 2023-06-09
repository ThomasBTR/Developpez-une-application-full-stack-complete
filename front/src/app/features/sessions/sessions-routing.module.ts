import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ThemeComponent} from "./components/theme/theme.component";

const routes: Routes = [
  {path: 'themes', title: 'Theme list', component: ThemeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SessionsRoutingModule {
}
