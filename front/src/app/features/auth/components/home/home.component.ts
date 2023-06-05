import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {SessionService} from 'src/app/services/session.service';
import {AuthService} from "../../services/auth.service";
import {FormBuilder} from "@angular/forms";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {}

  login() {
    this.router.navigate(['/login']);
  }

  register() {
    this.router.navigate(['/register']);
  }
}
