import { HttpClient,HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { StorageService } from './storage.service';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements OnInit {

  userLog: any;
  private token: string = '';

  constructor(private http: HttpClient, private storage: StorageService,
    private router: Router) {
  }

  async ngOnInit() {
  }

  async getLogUser(){
    this.userLog = await this.storage.get("user");
    const user = this.userLog ? JSON.parse(this.userLog) : { id: 0 };
    return user;
  }

  async login(user: { email: string; password: string; }): Promise<boolean> {
    if (user) {
      return this.http
        .post<boolean>(`${environment.API}/auth/login`, user)
        .toPromise()
        .then((result: any) => {
          console.log(result)
          this.token = result.token;
          this.storage.set("token", this.token);
          this.storage.set("user", result.user);
          return true;
        })
        .catch((err) => {
          this.token = '';
          return false;
        });
    }
    return false;
  }

  async logout() {
    await this.storage.remove("token");
    await this.storage.remove("user");
    this.router.navigate(['login']);
  }
}
