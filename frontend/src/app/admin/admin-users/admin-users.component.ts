import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html'
})
export class AdminUsersComponent implements OnInit {

  users: any[] = [];

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.adminService.getUsers().subscribe(res => this.users = res);
  }

  editRole(id: number) {
    this.router.navigate(['/admin/edit-role', id]);
  }

  deleteUser(id: number) {
    if (confirm("Delete this user?")) {
      this.adminService.deleteUser(id).subscribe(() => this.load());
    }
  }
}
