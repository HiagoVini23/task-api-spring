import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { FormControl, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgIf } from '@angular/common';
import { AuthenticationService } from '../utils/authentication.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatCardModule, MatFormFieldModule, NgIf,// Adicione aqui
    MatIconModule, MatInputModule, FormsModule, MatButtonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  hide = true;
  logging: boolean = true;

  constructor(private router: Router, private _snackBar: MatSnackBar, private authentication: AuthenticationService) {

  }

  async ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    })
  }

  async login() {
    if (this.loginForm.invalid) {
      this._snackBar.open('Preencha da forma correta os campos!', '', {
        duration: 3000
      });
      return
    }
    else {
      this.logging = await this.authentication.login({ email: this.email, password: this.password });
      if (this.logging)
        this.router.navigate(['/todo-list']);
      else {
        this._snackBar.open('Credencias inválidas!', '', {
          duration: 3000
        });
      }
    }
  }

  get email() {
    return this.loginForm.get('email')!.value;
  }

  get password() {
    return this.loginForm.get('password')!.value;
  }


}
