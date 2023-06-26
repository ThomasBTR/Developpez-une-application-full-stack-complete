import {Component, OnInit} from '@angular/core';
import {SessionInformation} from "../../../../interfaces/sessionInformation.interface";
import {SessionService} from "../../../../services/session.service";
import {Observable} from "rxjs";
import {Article} from "../../interfaces/article.interface";
import {ArticlesApiService} from "../../services/articles-api.service";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  public articles$: Observable<Article[]> = this.sessionApiService.getAllArticles();

  constructor(
    private sessionService: SessionService,
    private sessionApiService: ArticlesApiService,
  ) {
  }

  get user(): SessionInformation | undefined {
    return this.sessionService.sessionInformation;
  }

  ngOnInit(): void {
  }

}
