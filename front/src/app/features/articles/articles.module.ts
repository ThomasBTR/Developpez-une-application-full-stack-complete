import {NgModule} from '@angular/core';
import {CommonModule, registerLocaleData} from '@angular/common';
import {FlexLayoutModule} from '@angular/flex-layout';
import {ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import localeFr from '@angular/common/locales/fr';
import {ListComponent} from "./components/list/list.component";
import {ArticlesRoutingModule} from "./articles-routing.module";


registerLocaleData(localeFr);

const materialModules = [
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatSnackBarModule,
  MatSelectModule
];

@NgModule({
  declarations: [
    ListComponent
  ],
  imports: [
    CommonModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    ArticlesRoutingModule,
    ...materialModules
  ]
})
export class ArticlesModule {
}