import { Component } from '@angular/core';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html'
})
export class ForgotPasswordComponent {
  email = "";
  message = "";
  loading = false;

  constructor(private auth: AuthService) {}

  submit() {
    this.loading = true;
    this.auth.forgotPassword(this.email).subscribe({
      next: () => {
        this.message = "Password reset link sent to your email!";
        this.loading = false;
      },
      error: () => {
        this.message = "Email not found!";
        this.loading = false;
      }
    });
  }
}
