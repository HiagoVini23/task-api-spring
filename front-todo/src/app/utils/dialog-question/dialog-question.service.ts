import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogQuestionComponent } from './dialog-question.component';


@Injectable({
  providedIn: 'root'
})
export class DialogQuestionService {

  constructor(public dialog: MatDialog) { }

  async openDialogConfirmDelete(nomeTabela: string, identificator: string = '') {
    let dialogRef = this.dialog.open(DialogQuestionComponent, {
      width: '250px',
      data: {
        title: 'Excluir ' + nomeTabela,
        message: 'Você tem certeza que gostaria de excluir ',
        identificator: identificator,
        buttonClose: 'Cancelar',
        buttonConfirm: 'Confirmar',
      }
    });
    try {
      const result = await dialogRef.afterClosed().toPromise();
      if (result === 'Confirmar') {
        return true;
      } 
    } catch (err) {
      console.error(err);
    }
    return false;
  }

  async openDialogConfirmNextPhase(nomeTabela: string) {
    let dialogRef = this.dialog.open(DialogQuestionComponent, {
      width: '250px',
      data: {
        title: nomeTabela,
        message: 'Você tem certeza que gostaria dar avanço ao processo?',
        buttonClose: 'Cancelar',
        buttonConfirm: 'Confirmar',
      }
    });
    try {
      const result = await dialogRef.afterClosed().toPromise();
      if (result === 'Confirmar') {
        return true;
      } 
    } catch (err) {
      console.error(err);
    }
    return false;
  }

  async openDialogConfirmExit() {
    let dialogRef = this.dialog.open(DialogQuestionComponent, {
      width: '250px',
      data: {
        title: 'Você tem certeza?',
        message: 'Todos os dados preenchidos serão descartados.',
        buttonClose: 'Não',
        buttonConfirm: 'Sim',
      }
    });
    try {
      const result = await dialogRef.afterClosed().toPromise();
      if (result === 'Sim') {
        return true;
      } 
    } catch (err) {
      console.error(err);
    }
    return false;
  }

}
