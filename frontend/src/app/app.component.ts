import { Component } from '@angular/core';
import { AuthService } from './core/auth.service';

@Component({
  selector: 'app-root',
  template: `
    <div *ngIf="auth.isLoggedIn(); else authLayout">
      <header class="toolbar">
        <div class="toolbar-title">Smart Task Manager</div>
        <div class="toolbar-user">
          {{ auth.currentUserEmail }}
          <button class="button" (click)="logout()">Logout</button>
        </div>
      </header>
      <main class="page">
        <router-outlet></router-outlet>
      </main>
    </div>

    <ng-template #authLayout>
      <div class="app-shell">
        <router-outlet></router-outlet>
      </div>
    </ng-template>
  `
})
export class AppComponent {
  constructor(public auth: AuthService) {}

  logout() {
    this.auth.logout();
  }
}
