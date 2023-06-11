import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {SessionInformation} from '../../../../interfaces/sessionInformation.interface';
import {SessionService} from '../../../../services/session.service';
import {SessionApiService} from '../../services/session-api.service';
import {Theme} from "../../../../interfaces/theme.interface";

@Component({
  selector: 'app-themes',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss']
})
export class ThemeComponent implements OnInit{

  public themes$: Observable<Theme[]> = this.sessionApiService.allTheme();

  constructor(
    private sessionService: SessionService,
    private sessionApiService: SessionApiService
  ) { }

  get user(): SessionInformation | undefined {
    return this.sessionService.sessionInformation;
  }

  ngOnInit(): void {
  }
}
