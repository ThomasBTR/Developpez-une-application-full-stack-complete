import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {User} from "../../../../interfaces/user.interface";
import {UserService} from "../../../../services/user.service";
import {SessionService} from "../../../../services/session.service";
import {ArticlesApiService} from "../../services/articles-api.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {Theme} from "../../../../interfaces/theme.interface";
import {Observable} from "rxjs";
import {ThemesApiService} from "../../../themes/services/themes-api.service";
import {CreateInterface} from "../../interfaces/create.interface";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  public themes$ : Observable<Theme[]> = this.themesApiService.allTheme();
  public themesTable : Theme[] | undefined;
  public article : CreateInterface | undefined;
  public comments : Comment[] | undefined;
  public user : User | undefined;

  public form = this.fb.group({
    title: ['',
      [Validators.required,
        Validators.min(10)
      ]
    ],
    content: ['',
      [Validators.required,
        Validators.min(10)
      ]
    ],
    author: [''],
    theme: ['',
      [Validators.required,
        Validators.min(2)
      ]
    ]
  });

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private matSnackBar: MatSnackBar,
    private themesApiService: ThemesApiService,
    private articleApiService: ArticlesApiService,
    private router: Router,
    private sessionService: SessionService
  ) {
  }


  ngOnInit(): void {
    this.initForm()
  }

  initForm() {
    this.userService.getById(this.sessionService.sessionInformation!.id.toString()).subscribe((user: User) => {
      this.user = user;
    });
    this.themes$.subscribe((themes: Theme[]) => {
      this.themesTable = themes;
    });
  }

  postArticle(form: any) {
  const formArticle : CreateInterface = this.form.value as CreateInterface;
  if (this.user !== undefined) {
    formArticle.author = this.user.username;
  }
  this.articleApiService.postArticle(formArticle).subscribe((article: CreateInterface) => {
    this.router.navigate(['/articles']);
    this.matSnackBar.open('Article publié !', 'Close', {duration: 3000});
  });
  }

  orderBy() : void {


  }


}
