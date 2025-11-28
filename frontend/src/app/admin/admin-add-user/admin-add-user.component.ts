import { Component } from '@angular/core';
import { AdminService } from '../admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-user',
  templateUrl: './admin-add-user.component.html'
})
export class AdminAddUserComponent {
  fullName = '';
  email = '';
  password = '';
  role = 'EMPLOYEE';

  constructor(private adminService: AdminService, private router: Router) {}

  save() {
    const data = {
      fullName: this.fullName,
      email: this.email,
      password: this.password,
      role: this.role
    };

    this.adminService.addUser(data).subscribe(() => {
      alert("User added!");
      this.router.navigate(['/admin/users']);
    });
  }
}
