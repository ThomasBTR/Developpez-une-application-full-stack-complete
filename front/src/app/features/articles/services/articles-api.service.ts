import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Article} from "../interfaces/article.interface";
import {Comment} from "../interfaces/comment.interface";
import {CreateInterface} from "../interfaces/create.interface";


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

  public postComment(articleId: string, comment: Comment): Observable<Comment> {
    return this.httpClient.post<Comment>(`${this.pathService}/${articleId}/comment`, comment);
  }

  postArticle(formArticle: CreateInterface) : Observable<Article> {
    return this.httpClient.post<Article>(`${this.pathService}`, formArticle);
  }
}
