import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  email = '';
  password = '';
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  subscription: Subscription;
  constructor(
    private router: Router,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    // private auth: AuthService
  ) { }

  /**
   * On init
   */
  ngOnInit() {
    // if (sessionStorage.getItem("Token")) {
    //   this.router.navigate([""]);
    // }
  }

  /**
   * On destroy
   */
  ngOnDestroy(): void {

    // if (!isNullOrUndefined(this.subscription)) {
    //   this.subscription.unsubscribe();
    // }
  }

  /**
   * Login
   */
  loginEvent() {
    const user = {
      username: this.email,
      password: this.password
    };


    this.authService.login(this.email, this.password).subscribe(data => {
      console.log(data);
      if (data.accessToken !== '') {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(['/overview']);
      }


    }, err => {
      this.errorMessage = err.error.message;
      this.isLoginFailed = true;
    });
    // this.auth.checkAdmin(user, this.loginSuccess, this.loginFailed);
  }





}
