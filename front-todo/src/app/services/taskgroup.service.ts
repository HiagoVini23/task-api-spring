import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { AuthenticationService } from '../utils/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TaskGroupService {

  constructor(private http: HttpClient) { }

  async getTasksGroupByUserId(userId: number): Promise<any> {
    try {
      const response = await this.http
        .get(`${environment.API}/taskgroup/user/${userId}`)
        .toPromise();
      return response;
    } catch (error) {
      return error
    }
  }

  async deleteTaskGroup(taskGroupId: number): Promise<any> {
    try {
      const response = await this.http
        .delete(`${environment.API}/taskgroup/${taskGroupId}`)
        .toPromise();
      return response;
    } catch (error) {
      return error
    }
  }

  async createTaskGroup(taskGroup: any): Promise<any>  {
    try {
      const response = await this.http
        .post(`${environment.API}/taskgroup`, taskGroup)
        .toPromise()
      return response;
    } catch (error) {
      return error
    }
  }

  async editTaskGroup(taskGroupId: number, taskGroup: any): Promise<any>  {
    try {
      const response = await this.http
        .put(`${environment.API}/taskgroup/${taskGroupId}`, taskGroup)
        .toPromise()
      return response;
    } catch (error) {
      return error
    }
  }

}
