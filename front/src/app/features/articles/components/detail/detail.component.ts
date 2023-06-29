import {Component, OnInit} from '@angular/core';
import {Article} from "../../interfaces/article.interface";
import {ArticlesApiService} from "../../services/articles-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Comment} from "../../interfaces/comment.interface";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {

  public articleId: string;
  public article: Article | undefined;
  public comments: Comment[] | undefined;

  constructor(
              private route: ActivatedRoute,
              private router: Router,
              private articleApiService: ArticlesApiService,
  ) {
  this.articleId = this.route.snapshot.paramMap.get('id')!;

}

  ngOnInit(): void {
    this.fetchArticle();
  }

  fetchArticle(): void {
    this.articleApiService
      .getArticle(this.articleId)
      .subscribe((article: Article) => {
        this.article = article;
        this.comments = article.comments;
      });
  }
}
