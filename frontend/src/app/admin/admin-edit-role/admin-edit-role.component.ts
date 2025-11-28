import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-admin-edit-role',
  templateUrl: './admin-edit-role.component.html'
})
export class AdminEditRoleComponent implements OnInit {

  userId!: number;
  role = 'EMPLOYEE';

  constructor(
    private route: ActivatedRoute,
    private adminService: AdminService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get("id"));
  }

  save() {
    this.adminService.updateRole(this.userId, this.role)
      .subscribe(() => {
        alert("Role updated!");
        this.router.navigate(['/admin/users']);
      });
  }
}
