import { Component, Inject, ViewEncapsulation } from '@angular/core';
import {MatDialog, MatDialogRef, MatDialogModule, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dialog-question',
  templateUrl: './dialog-question.component.html',
  styleUrls: ['./dialog-question.component.scss'],
  imports: [
    CommonModule,
    MatDialogModule, // Importa o módulo de diálogo
    MatButtonModule, // Importa o módulo de botões
  ],
  standalone: true
})
export class DialogQuestionComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}
}
