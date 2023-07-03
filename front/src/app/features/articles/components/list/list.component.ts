import {Component} from '@angular/core';
import {SessionInformation} from "../../../../interfaces/sessionInformation.interface";
import {SessionService} from "../../../../services/session.service";
import {map, Observable} from "rxjs";
import {Article} from "../../interfaces/article.interface";
import {ArticlesApiService} from "../../services/articles-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderBy} from "../../interfaces/OrderByList.interface";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public articles$: Observable<Article[]> = this.sessionApiService.getAllArticles();
  public orderByList: OrderBy[] | undefined;
  public articleList: Article[] | undefined;

  constructor(
    private sessionService: SessionService,
    private sessionApiService: ArticlesApiService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.orderByList = [{
      menuLinkText: 'Thèmes'
    },
      {
        menuLinkText: 'Date'
      },
      {
        menuLinkText: 'Auteur'
      }
    ];
  }

  get user(): SessionInformation | undefined {
    return this.sessionService.sessionInformation;
  }

  add() {
    this.router.navigate(['/articles/create']);
  }

  orderArticlesBy(orderBy: string) {
    if (orderBy === 'Thèmes') {
      this.articles$.subscribe((articles) => {
        this.articles$ = this.articles$.pipe(
          map((articles) => {
            articles.sort((a, b) => {
              return a.theme > b.theme ? 1 : -1;
            });
            return articles;
          }));
      });
    } else if (orderBy === 'Date') {
      this.articles$.subscribe((articles) => {
        this.articles$ = this.articles$.pipe(
          map((articles) => {
            articles.sort((a, b) => {
              return a.date > b.date ? 1 : -1;
            });
            return articles;
          }));
      });
    } else if (orderBy === 'Auteur') {
      this.articles$.subscribe((articles) => {
        this.articles$ = this.articles$.pipe(
          map((articles) => {
            articles.sort((a, b) => {
              return a.author > b.author ? 1 : -1;
            });
            return articles;
          }));
      });
    }
  }
}
