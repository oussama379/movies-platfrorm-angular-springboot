import {Component, Inject, Injectable} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MessageBoxComponent} from "../message-box/message-box.component";

@Injectable({
  providedIn: 'root'
})
export class MessageBoxService {


  constructor(private dialog: MatDialog) {
  }

  openDialog(title?: string, message?: string) {
    this.dialog.open(MessageBoxComponent, {
      data: {
        message,
        title
      },
    });
  }


}
