import {Component, OnInit} from '@angular/core';
import {Article} from "../../interfaces/article.interface";
import {ArticlesApiService} from "../../services/articles-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Comment} from "../../interfaces/comment.interface";
import {FormBuilder} from "@angular/forms";
import {SessionService} from "../../../../services/session.service";
import {User} from "../../../../interfaces/user.interface";
import {UserService} from "../../../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {

  public articleId: string;
  public article: Article | undefined;
  public comments: Comment[] | undefined;
  public user : User | undefined;

  public form = this.fb.group({
    content: ['']
  });

  constructor(
              private route: ActivatedRoute,
              private router: Router,
              private articleApiService: ArticlesApiService,
              private matSnackBar: MatSnackBar,
              private fb: FormBuilder,
              private userService: UserService,
              private sessionService: SessionService
  ) {
  this.articleId = this.route.snapshot.paramMap.get('id')!;

}

  ngOnInit(): void {
    this.fetchArticle();
  }

  public back(): void {
    window.history.back();
  }

  fetchArticle(): void {
    this.articleApiService
      .getArticle(this.articleId)
      .subscribe((article: Article) => {
        this.article = article;
        this.comments = article.comments;
      });
    this.userService
      .getById(this.sessionService.sessionInformation!.id.toString())
      .subscribe((user: User) => {
        this.user = user;
      });
  }

  postComment(form: any) {
    const formComment : Comment = this.form.value as Comment;
    if (this.user !== undefined) {
      formComment.username = this.user.username;
    }
    this.articleApiService.postComment(this.articleId, formComment)
      .subscribe( {
      next: (response : Comment  ) => {
        this.matSnackBar.open('Commentaire envoyé !', 'Close', {duration: 3000});
        this.fetchArticle();
      }
    });
  }
}
