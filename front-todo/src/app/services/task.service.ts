import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { AuthenticationService } from '../utils/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  async getTasksByTaskGroupId(taskGroupId: number): Promise<any> {
    try {
      const response = await this.http
        .get(`${environment.API}/task/taskgroup/${taskGroupId}`)
        .toPromise();
      return response;
    } catch (error) {
      return error
    }
  }

  async deleteTask(taskId: number): Promise<any> {
    try {
      const response = await this.http
        .delete(`${environment.API}/task/${taskId}`)
        .toPromise();
      return response;
    } catch (error) {
      return error
    }
  }

  async createTask(task: any): Promise<any>  {
    try {
      const response = await this.http
        .post(`${environment.API}/task`, task)
        .toPromise()
      return response;
    } catch (error) {
      return error
    }
  }

  async editTask(taskId: number, task: any): Promise<any>  {
    try {
      const response = await this.http
        .put(`${environment.API}/task/${taskId}`, task)
        .toPromise()
      return response;
    } catch (error) {
      return error
    }
  }

}