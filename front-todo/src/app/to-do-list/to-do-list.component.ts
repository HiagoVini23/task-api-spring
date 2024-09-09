import { Component, OnInit, signal, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CommonModule, NgFor, NgIf, NgStyle } from '@angular/common';
import { MatFormFieldModule } from "@angular/material/form-field";
import { FormControl, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthenticationService } from '../utils/authentication.service';
import { TaskService } from '../services/task.service';
import { TaskGroupService } from '../services/taskgroup.service';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { DialogQuestionService } from '../utils/dialog-question/dialog-question.service';

@Component({
  selector: 'app-to-do-list',
  standalone: true,
  imports: [MatButtonModule, MatSnackBarModule, MatListModule, MatExpansionModule, MatPaginatorModule,
    MatCardModule, MatIconModule, MatTooltipModule, MatInputModule, CommonModule, // Necessário para diretivas como *ngIf
    MatCheckboxModule, NgIf, NgFor, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatToolbarModule, NgStyle],
  templateUrl: './to-do-list.component.html',
  styleUrl: './to-do-list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToDoListComponent implements OnInit {
  openInput = false;
  taskGroupForm!: FormGroup;
  tasksGroup!: any[];
  pageSize = 4;
  pageIndex = 0;
  pageEvent!: PageEvent;
  userId: number = 0;
  currentPageTasksGroup!: any[];
  readonly panelOpenState = signal(false);
  expandedTaskGroupId: number | null = null;

  constructor(private authentication: AuthenticationService, private taskService: TaskService,
    private taskGroupService: TaskGroupService, private _snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef, public dialogQuestionService: DialogQuestionService) {

  }

  async ngOnInit() {
    this.taskGroupForm = new FormGroup({
        title: new FormControl('', [Validators.required])
    });
    this.userId = (await this.authentication.getLogUser()).id;
    this.currentPageTasksGroup = [];
    this.findAll();
}



  handlePageEvent(e: PageEvent) {
    if (e && e.pageSize !== undefined && e.pageIndex !== undefined) {
      this.pageEvent = e;
      this.pageSize = e.pageSize;
      this.pageIndex = e.pageIndex;

      if (this.tasksGroup && this.tasksGroup.length > 0) {
        const startIndex = this.pageIndex * this.pageSize;
        const endIndex = Math.min(startIndex + this.pageSize, this.tasksGroup.length);
        this.currentPageTasksGroup = this.tasksGroup.slice(startIndex, endIndex);
      } else {
        this.currentPageTasksGroup = []; // Define como um array vazio se não houver dados
      }
    } else {
      console.error('pageEvent não possui pageSize ou pageIndex.');
    }
  }



  async findAll(): Promise<void> {
    this.tasksGroup = await this.taskGroupService.getTasksGroupByUserId(this.userId);
    if (this.tasksGroup && this.tasksGroup.length > 0) {
      this.currentPageTasksGroup = this.tasksGroup.slice(this.pageIndex * this.pageSize,
        (this.pageIndex + 1) * this.pageSize);
    } else {
      this.currentPageTasksGroup = [];
    }
  }


  logout() {
    this.authentication.logout();
  }

  async createTaskGroup() {
    if (this.taskGroupForm.invalid) {
      this._snackBar.open('Preencha da forma correta os campos!', '', {
        duration: 3000
      });
      return;
    }
    try {
      await this.taskGroupService.createTaskGroup({ title: this.title, user: { id: this.userId } });
      this.openInput = false;
      this.taskGroupForm.reset();
      await this.refreshList();
    } catch (error) {
      console.error('Erro ao criar o grupo de tarefas:', error);
    }
  }

  async deleteTaskGroup(taskGroupId: number) {
    const res = await this.dialogQuestionService.openDialogConfirmDelete('TaskGroup', '');
    if (res) {
      await this.taskGroupService.deleteTaskGroup(taskGroupId);
      this._snackBar.open('TaskGroup excluido com sucesso!', '', {
        duration: 3000
      });
      await this.refreshList();
      return;
    }
  }

  async refreshList() {
    await this.findAll();
    this.pageIndex = 0;
    this.handlePageEvent({ pageIndex: this.pageIndex, pageSize: this.pageSize } as PageEvent);
    this.cdr.detectChanges();
  }

  onPanelOpened(taskGroupId: number) {
    if (this.expandedTaskGroupId !== taskGroupId) {
      this.expandedTaskGroupId = taskGroupId;
      this.loadTasks(taskGroupId);
    }
  }

  onPanelClosed(id: number): void {
    if (this.expandedTaskGroupId === id) {
      this.expandedTaskGroupId = null; // Fecha o painel se ele for o atual
    }
  }

  loadTasks(taskGroupId: number) {
    this.taskService.getTasksByTaskGroupId(taskGroupId)
      .then(tasks => {
        const taskGroup = this.currentPageTasksGroup.find((group: any) => group.id === taskGroupId);
        if (taskGroup) {
          taskGroup.tasks = tasks; // Atualiza o grupo de tarefas com as tarefas recebidas
        }
      })
      .catch(error => {
        console.error('Erro ao carregar tarefas:', error);
      });
  }

  get title() {
    return this.taskGroupForm.get('title')!.value;
  }

}
