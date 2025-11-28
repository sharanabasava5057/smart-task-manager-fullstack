import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html'
})
export class ResetPasswordComponent {

  token = "";
  password = "";
  confirmPassword = "";
  message = "";
  loading = false;

  constructor(private route: ActivatedRoute, private auth: AuthService) {
    this.token = this.route.snapshot.queryParamMap.get("token") || "";
  }

  reset() {
    if (this.password !== this.confirmPassword) {
      this.message = "Passwords do not match!";
      return;
    }

    this.loading = true;
    this.auth.resetPassword(this.token, this.password).subscribe({
      next: () => {
        this.message = "Password updated successfully!";
        this.loading = false;
      },
      error: () => {
        this.message = "Invalid or expired token!";
        this.loading = false;
      }
    });
  }
}
