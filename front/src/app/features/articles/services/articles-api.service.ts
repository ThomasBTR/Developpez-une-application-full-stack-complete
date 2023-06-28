import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Article} from "../interfaces/article.interface";


@Injectable({
  providedIn: 'root'
})
export class ArticlesApiService {

  private pathService = 'api/articles';

  constructor(private httpClient: HttpClient) {
  }

  public getAllArticles(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.pathService);
  }

  public getArticle(id: string): Observable<Article> {
    return this.httpClient.get<Article>(`${this.pathService}/${id}`);
  }
}
