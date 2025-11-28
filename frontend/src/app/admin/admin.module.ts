import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminAddUserComponent } from './admin-add-user/admin-add-user.component';
import { AdminEditRoleComponent } from './admin-edit-role/admin-edit-role.component';
import { AdminLogsComponent } from './admin-logs/admin-logs.component';

@NgModule({
  declarations: [
    AdminUsersComponent,
    AdminAddUserComponent,
    AdminEditRoleComponent,
    AdminLogsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule {}
