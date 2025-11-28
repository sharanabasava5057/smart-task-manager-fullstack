import { Component, AfterViewInit } from '@angular/core';
import { DashboardService, EmployeeCompletion } from '../../core/dashboard.service';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements AfterViewInit {

  overdueTasks: any[] = [];
  upcomingTasks: any[] = [];

  constructor(private dashboardService: DashboardService) {}

  ngAfterViewInit(): void {
    this.loadStatusChart();
    this.loadEmployeeChart();
    this.loadTaskLists();
  }

  loadStatusChart() {
    this.dashboardService.getStatusDistribution().subscribe(data => {
      const ctx = document.getElementById('statusChart') as HTMLCanvasElement;

      new Chart(ctx, {
        type: 'pie',
        data: {
          labels: Object.keys(data),
          datasets: [
            {
              data: Object.values(data)
            }
          ]
        }
      });
    });
  }

  loadEmployeeChart() {
    this.dashboardService.getCompletionByEmployee()
      .subscribe((rows: EmployeeCompletion[]) => {
        const ctx = document.getElementById('employeeChart') as HTMLCanvasElement;

        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: rows.map(r => r.employeeName),
            datasets: [
              {
                data: rows.map(r => r.count)
              }
            ]
          }
        });
      });
  }

  loadTaskLists() {
    this.dashboardService.getOverdueTasks().subscribe(t => this.overdueTasks = t);
    this.dashboardService.getUpcomingTasks().subscribe(t => this.upcomingTasks = t);
  }
}
