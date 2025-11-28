import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ReportsService {

  private baseUrl = `${environment.apiUrl}/api/reports`;

  constructor(private http: HttpClient) {}

  downloadTasksByStatusPdf(status: string) {
    const url = `${this.baseUrl}/tasks/status/pdf?status=${status}`;
    return this.http.get(url, { responseType: 'blob' });
  }

  downloadTasksByStatusExcel(status: string) {
    const url = `${this.baseUrl}/tasks/status/excel?status=${status}`;
    return this.http.get(url, { responseType: 'blob' });
  }

  downloadTasksByRangePdf(start: string, end: string) {
    const url = `${this.baseUrl}/tasks/range/pdf?start=${start}&end=${end}`;
    return this.http.get(url, { responseType: 'blob' });
  }

  downloadTasksByRangeExcel(start: string, end: string) {
    const url = `${this.baseUrl}/tasks/range/excel?start=${start}&end=${end}`;
    return this.http.get(url, { responseType: 'blob' });
  }
}
