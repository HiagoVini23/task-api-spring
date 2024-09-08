import { NgIf, NgFor, NgStyle } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { AuthenticationService } from '../utils/authentication.service';

@Component({
  selector: 'app-task-group',
  standalone: true,
  imports: [MatButtonModule, MatPaginatorModule, MatCardModule, MatIconModule, MatTooltipModule, MatInputModule,
    MatCheckboxModule, NgIf, NgFor, MatFormFieldModule,FormsModule, ReactiveFormsModule, MatToolbarModule, NgStyle ],
  templateUrl: './task-group.component.html',
  styleUrl: './task-group.component.css'
})
export class TaskGroupComponent {

  constructor(private authentication: AuthenticationService){

  }

  logout(){
    this.authentication.logout();
  }

}
