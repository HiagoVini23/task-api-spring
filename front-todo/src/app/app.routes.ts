import { Routes } from '@angular/router';
import { ToDoListComponent } from './to-do-list/to-do-list.component';
import { LoginComponent } from './login/login.component';
import { TaskGroupComponent } from './task-group/task-group.component';

export const routes: Routes = [
    {path: 'todo-list', component: ToDoListComponent},
    {path: 'tasksgroup', component: TaskGroupComponent},
    {path: 'login', component: LoginComponent},
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];