// import { AuthService } from "./../../../core/service/auth.service";
import { Component, OnInit } from "@angular/core";
import { validateEmail } from "src/app/helper/Commonfeature";

@Component({
  selector: "app-dialog-add-new-user",
  templateUrl: "./dialog-add-new-user.component.html",
  styleUrls: ["./dialog-add-new-user.component.scss"]
})
export class DialogAddNewUserComponent implements OnInit {
  username: string;
  email: string;
  name: string;
  password: string;
  role: string;
  alertWarning: string;
  alertSaved: string;

  constructor(
    // private authServe: AuthService
  ) {
    this.alertWarning = "";
    this.alertSaved = "";
  }

  ngOnInit() {}

  /**
   * Event add new user
   */
  addNewUser() {
    // if (
    //   isNullOrUndefined(this.username) ||
    //   isNullOrUndefined(this.email) ||
    //   isNullOrUndefined(this.name) ||
    //   isNullOrUndefined(this.password) ||
    //   isNullOrUndefined(this.role)
    // ) {
    //   this.alertWarning = "Bạn cần nhập đầy đủ thông tin!";

    //   return;
    // }
    // if (!validateEmail(this.email)) {
    //   this.alertWarning = "Email chưa đúng !";
    //   return;
    // }

    // const user = {
    //   userName: this.username,
    //   email: this.email,
    //   fullName: this.name,
    //   passWord: this.password,
    //   role: this.role
    // };
    // this.authServe.addNewUser(user, this.addNewSuccess, this.addNewFailed);
  }

  /**
   * Add new success
   */
  addNewSuccess = () => {
    this.alertWarning = "";
    this.alertSaved = "Thêm người dùng thành công!";
  };

  /**
   * Add new failed
   */
  addNewFailed = () => {
    this.alertSaved = "";
    this.alertWarning = "Thêm người dùng thất bại!";
  };
}
