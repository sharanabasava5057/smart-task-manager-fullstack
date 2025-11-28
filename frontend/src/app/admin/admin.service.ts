import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AdminService {

  constructor(private http: HttpClient) {}

  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/api/admin/users`);
  }

  addUser(data: any): Observable<any> {
    return this.http.post(`${environment.apiUrl}/api/admin/add`, data);
  }

  updateRole(id: number, role: string): Observable<any> {
    return this.http.put(`${environment.apiUrl}/api/admin/update-role/${id}`, { role });
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${environment.apiUrl}/api/admin/delete/${id}`);
  }

  getLogs(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/api/admin/logs`);
  }
}
