import {Component, OnInit} from '@angular/core';
import {SessionInformation} from "../../../../interfaces/sessionInformation.interface";
import {SessionService} from "../../../../services/session.service";
import {Observable} from "rxjs";
import {Article} from "../../interfaces/article.interface";
import {ArticlesApiService} from "../../services/articles-api.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public articles$: Observable<Article[]> = this.sessionApiService.getAllArticles();

  constructor(
    private sessionService: SessionService,
    private sessionApiService: ArticlesApiService,
    private route: ActivatedRoute,
  ) {}

  get user(): SessionInformation | undefined {
    return this.sessionService.sessionInformation;
  }
}
