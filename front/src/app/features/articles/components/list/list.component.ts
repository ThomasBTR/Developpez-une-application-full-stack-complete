import {Component, OnInit} from '@angular/core';
import {SessionInformation} from "../../../../interfaces/sessionInformation.interface";
import {SessionService} from "../../../../services/session.service";
import {map, Observable} from "rxjs";
import {Article} from "../../interfaces/article.interface";
import {ArticlesApiService} from "../../services/articles-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderBy} from "../../interfaces/OrderByList.interface";
import {UserService} from "../../../../services/user.service";
import {User} from "../../../../interfaces/user.interface";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  public articles$: Observable<Article[]> = this.sessionApiService.getAllArticles();
  public orderByList: OrderBy[] | undefined;
  private userInfo: User | undefined;
  private themeList: String[] | undefined;

  constructor(
    private sessionService: SessionService,
    private sessionApiService: ArticlesApiService,
    private userService: UserService,
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

  ngOnInit() {
    this.userService.getById(this.sessionService.sessionInformation!.id.toString()).subscribe((user) => {
      this.themeList = user.subscriptions?.map((subscription) => {
        return subscription.title;
      });
      if (this.themeList) {
        this.articles$ = this.articles$.pipe(
          map((articles) => {
            return articles.filter((article) => {
              return this.themeList?.includes(article.theme);
            });
          }));
      }
      ;
    });
  }

  get user(): SessionInformation | undefined {
    return this.sessionService.sessionInformation;
  }

  add() {
    this.router.navigate(['/articles/create']);
  }

  orderArticlesBy(orderBy: string) {
    switch (orderBy) {
      case 'Thèmes':
        this.orderByThemes(this.orderByList);
        break;
      case 'Date':
        this.orderByDate(this.orderByList);
        break;
      case 'Auteur':
        this.orderByAuthor(this.orderByList);
        break;
    }
  }

  private orderByAuthor(orderByList: OrderBy[] | undefined) {
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

  private orderByDate(orderByList: OrderBy[] | undefined) {
    this.articles$.subscribe((articles) => {
      this.articles$ = this.articles$.pipe(
        map((articles) => {
          articles.sort((a, b) => {
            return a.date > b.date ? 1 : -1;
          });
          return articles;
        }));
    });
  }

  private orderByThemes(orderByList: OrderBy[] | undefined): void {
    this.articles$.subscribe((articles) => {
      this.articles$ = this.articles$.pipe(
        map((articles) => {
          articles.sort((a, b) => {
            return a.theme > b.theme ? 1 : -1;
          });
          return articles;
        }));
    });
  }
}
