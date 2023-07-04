import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {User} from '../../interfaces/user.interface';
import {SessionService} from '../../services/session.service';
import {UserService} from '../../services/user.service';
import {Subscription} from "../../interfaces/subscription.interface";
import {ThemesApiService} from "../../features/themes/services/themes-api.service";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user: User | undefined;
  subscriptions: Subscription[] | undefined;
  public onError: boolean = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required]]
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

  unsubscribeOnTheme(themeId: number) {
    this.themesApiService.unsubscribeOnTheme(themeId, this.sessionService.sessionInformation!.id)
      .subscribe((_: any) => {
        this.matSnackBar.open('Theme retiré !', 'Close', {duration: 3000});
        this.ngOnInit();
      });
  }

  updateUser(form: any) {
    const formUser: User = this.form.value as User;
    this.userService.update(this.sessionService.sessionInformation!.id, formUser).subscribe({
      next: (response: User) => {
        this.matSnackBar.open('User updated !', 'Close', {duration: 3000});
        this.user = response;
        this.subscriptions = this.user.subscriptions;
      },
      error: (error: any) => this.onError = true,
    });
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']).then(r => true);
  }
}
