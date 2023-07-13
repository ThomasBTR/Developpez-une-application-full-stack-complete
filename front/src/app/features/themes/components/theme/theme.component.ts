import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {SessionInformation} from '../../../../interfaces/sessionInformation.interface';
import {SessionService} from '../../../../services/session.service';
import {ThemesApiService} from '../../services/themes-api.service';
import {Theme} from "../../../../interfaces/theme.interface";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-themes',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss']
})
export class ThemeComponent implements OnInit {

  public themes$: Observable<Theme[]> = this.sessionApiService.allTheme();

  constructor(
    private sessionService: SessionService,
    private sessionApiService: ThemesApiService,
    private matSnackBar: MatSnackBar
  ) {
  }

  get user(): SessionInformation | undefined {
    return this.sessionService.sessionInformation;
  }

  ngOnInit(): void {
  }


  subscribeOnTheme(themeId: number) {
    this.sessionApiService.subscribeOnTheme(themeId, this.user?.id)
      .subscribe((_: any) => {
        this.matSnackBar.open('Theme ajouté !', 'Close', {duration: 3000});
        this.themes$ = this.sessionApiService.allTheme();
      });

  }
}
