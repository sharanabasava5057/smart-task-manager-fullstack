import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-admin-logs',
  templateUrl: './admin-logs.component.html'
})
export class AdminLogsComponent implements OnInit {

  logs: any[] = [];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getLogs().subscribe(res => this.logs = res);
  }
}
