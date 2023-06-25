import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Session } from '../interfaces/session.interface';
import {Theme} from "../../../interfaces/theme.interface";


@Injectable({
  providedIn: 'root'
})
export class ThemesApiService {

  private pathService = 'api/themes';

  constructor(private httpClient: HttpClient) {
  }

  public allTheme(): Observable<Theme[]> {
    return this.httpClient.get<Theme[]>(this.pathService);
  }

  subscribeOnTheme(themeId: number, userId: number | undefined) {
    return this.httpClient.post<void>(`${this.pathService}/${themeId}/subscribe/${userId}`, null);
  }

  unsubscribeOnTheme(themeId: number, userId: number | undefined) {
    return this.httpClient.delete(`${this.pathService}/${themeId}/subscribe/${userId}`);
  }
}
