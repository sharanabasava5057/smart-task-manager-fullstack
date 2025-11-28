import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminAddUserComponent } from './admin-add-user/admin-add-user.component';
import { AdminEditRoleComponent } from './admin-edit-role/admin-edit-role.component';
import { AdminLogsComponent } from './admin-logs/admin-logs.component';
import { AuthGuard } from '../core/auth.guard';

const routes: Routes = [
  { path: 'users', component: AdminUsersComponent, canActivate: [AuthGuard] },
  { path: 'add-user', component: AdminAddUserComponent, canActivate: [AuthGuard] },
  { path: 'edit-role/:id', component: AdminEditRoleComponent, canActivate: [AuthGuard] },
  { path: 'logs', component: AdminLogsComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
