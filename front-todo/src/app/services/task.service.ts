import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { AuthenticationService } from '../utils/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  async getTasks(userId: number): Promise<any> {
    try {
      const response = await this.http
        .get(`${environment.API}/task/all/${userId}`)
        .toPromise();
      return response;
    } catch (error) {
      return error
    }
  }

  async deleteTask(userId: number, taskId: number): Promise<any> {
    try {
      const response = await this.http
        .delete(`${environment.API}/task/delete/${userId}/${taskId}`)
        .toPromise();
      return response;
    } catch (error) {
      return error
    }
  }

  async createTask(task: any): Promise<any>  {
    try {
      const response = await this.http
        .post(`${environment.API}/task/create`, task)
        .toPromise()
      return response;
    } catch (error) {
      return error
    }
  }

  async editTask(userId: number, taskId: number, task: any): Promise<any>  {
    try {
      const response = await this.http
        .put(`${environment.API}/task/update/${userId}/${taskId}`, task)
        .toPromise()
      return response;
    } catch (error) {
      return error
    }
  }

}
