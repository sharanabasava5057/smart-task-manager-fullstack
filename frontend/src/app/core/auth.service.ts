import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

interface LoginRequest {
  email: string;
  password: string;
}

interface RegisterRequest {
  fullName: string;
  email: string;
  password: string;
}

interface AuthResponse {
  token: string;
  roles: string[];
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly TOKEN_KEY = 'stm_token';
  private readonly ROLES_KEY = 'stm_roles';
  private readonly EMAIL_KEY = 'stm_email';

  private baseUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient, private router: Router) {}

  // --------------------------
  // REGISTER
  // --------------------------
  register(data: RegisterRequest) {
    return this.http.post(`${this.baseUrl}/register`, data);
  }

  // --------------------------
  // LOGIN
  // --------------------------
  login(data: LoginRequest) {
    return this.http.post<AuthResponse>(`${this.baseUrl}/login`, data);
  }

  // --------------------------
  // LOGOUT
  // --------------------------
  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  // --------------------------
  // CHECK LOGGED IN
  // --------------------------
  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }

  // --------------------------
  // SAVE USER INFO
  // --------------------------
  saveAuth(token: string, roles: string[], email: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
    localStorage.setItem(this.ROLES_KEY, JSON.stringify(roles));
    localStorage.setItem(this.EMAIL_KEY, email);
  }

  // --------------------------
  // GET TOKEN
  // --------------------------
  get token(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  // --------------------------
  // GET USER EMAIL
  // --------------------------
  get currentUserEmail(): string | null {
    return localStorage.getItem(this.EMAIL_KEY);
  }

  // --------------------------
  // FORGOT PASSWORD
  // --------------------------
  forgotPassword(email: string) {
    return this.http.post(`${this.baseUrl}/forgot-password`, { email });
  }

  // --------------------------
  // RESET PASSWORD
  // --------------------------
  resetPassword(token: string, newPassword: string) {
    return this.http.post(`${this.baseUrl}/reset-password`, {
      token,
      newPassword
    });
  }
}
