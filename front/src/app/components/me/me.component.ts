import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from '../../interfaces/user.interface';
import { SessionService } from '../../services/session.service';
import { UserService } from '../../services/user.service';
import {Subscription} from "../../interfaces/subscription.interface";
import {Observable} from "rxjs";
import {Theme} from "../../interfaces/theme.interface";
import {ThemesApiService} from "../../features/themes/services/themes-api.service";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user: User | undefined;
  subscriptions : Subscription[] | undefined

  public form = this.fb.group({
    username: [
      '',
      [
        Validators.required,
        Validators.min(3)
      ]
    ],
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ]
  });

  constructor(private router: Router,
              private sessionService: SessionService,
              private matSnackBar: MatSnackBar,
              private userService: UserService,
              private themesApiService: ThemesApiService,
              private fb: FormBuilder) {
  }

  public ngOnInit(): void {
    this.userService
      .getById(this.sessionService.sessionInformation!.id.toString())
      .subscribe((user: User) => {
        this.user = user;
        this.subscriptions = this.user.subscriptions;
      });
  }

  public back(): void {
    window.history.back();
  }

  public delete(): void {
    this.userService
      .delete(this.sessionService.sessionInformation!.id.toString())
      .subscribe((_) => {
        this.matSnackBar.open("Your account has been deleted !", 'Close', { duration: 3000 });
        this.sessionService.logOut();
        this.router.navigate(['/']);
      })
  }

  unsubscribeOnTheme(themeId : number) {
    this.themesApiService.unsubscribeOnTheme(themeId, this.sessionService.sessionInformation!.id)
      .subscribe((_: any) => {
    this.matSnackBar.open('Theme retiré !', 'Close', {duration: 3000});
    this.ngOnInit();
  });
  }

  updateUser(form: any) {
    this.userService.update(this.sessionService.sessionInformation!.id, form.value)
      .subscribe((_: any) => {
        this.matSnackBar.open('User updated !', 'Close', {duration: 3000});
        this.ngOnInit();
      });
  }

  }
}
