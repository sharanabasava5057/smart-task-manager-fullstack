import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface EmployeeCompletion {
  employeeName: string;
  count: number;
}

@Injectable({ providedIn: 'root' })
export class DashboardService {

  constructor(private http: HttpClient) {}

  getStatusDistribution(): Observable<{ [status: string]: number }> {
    return this.http.get<{ [status: string]: number }>(
      `${environment.apiUrl}/api/dashboard/status-distribution`
    );
  }

  getCompletionByEmployee(): Observable<EmployeeCompletion[]> {
    return this.http.get<EmployeeCompletion[]>(
      `${environment.apiUrl}/api/dashboard/completion-by-employee`
    );
  }

  getOverdueTasks() {
    return this.http.get<any[]>(`${environment.apiUrl}/api/dashboard/overdue`);
  }

  getUpcomingTasks() {
    return this.http.get<any[]>(`${environment.apiUrl}/api/dashboard/upcoming`);
  }
}
