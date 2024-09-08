import { Component, OnInit } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { NgFor, NgIf, NgStyle } from '@angular/common';
import { MatFormFieldModule } from "@angular/material/form-field";
import { FormControl, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AuthenticationService } from '../utils/authentication.service';
import { TaskService } from '../services/task.service';
import { TaskGroupService } from '../services/taskgroup.service';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-to-do-list',
  standalone: true,
  imports: [MatButtonModule, MatPaginatorModule, MatCardModule, MatIconModule, MatTooltipModule, MatInputModule,
    MatCheckboxModule, NgIf, NgFor, MatFormFieldModule,FormsModule, ReactiveFormsModule, MatToolbarModule, NgStyle ],
  templateUrl: './to-do-list.component.html',
  styleUrl: './to-do-list.component.css'
})
export class ToDoListComponent implements OnInit {
  openInput = false;
  taskForm!: FormGroup;
  tasksGroup: any;
  pageSize = 4;
  pageIndex = 0;
  pageEvent!: PageEvent;
  currentPageTasksGroup: any;

  constructor(private authentication: AuthenticationService, private taskService: TaskService,
    private taskGroupService: TaskGroupService){

  }

  async ngOnInit() {
    this.taskForm = new FormGroup({
      description: new FormControl('', [Validators.required])
    })
    this.findAll()
  }

  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    const startIndex = this.pageIndex * this.pageSize;
    const endIndex = Math.min(startIndex + this.pageSize, this.tasksGroup.length);
    // Obter os itens a serem exibidos na página atual
    this.currentPageTasksGroup = this.tasksGroup.slice(startIndex, endIndex);
  }

  async findAll(){
    this.tasksGroup = await this.taskGroupService.getTasksGroupByUserId((await this.authentication.getLogUser()).id)
    console.log(this.tasksGroup)
    this.currentPageTasksGroup = this.tasksGroup.slice(this.pageIndex, this.pageSize);
  }

  logout(){
    this.authentication.logout();
  }

  createTaskGroup(){

  }

}
