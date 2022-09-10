import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-message-box',
  templateUrl: './message-box.component.html',
  styleUrls: ['./message-box.component.css']
})
export class MessageBoxComponent implements OnInit {

  title = '';
  message = '';


  constructor(public dialogRef: MatDialogRef<MessageBoxComponent>, @Inject(MAT_DIALOG_DATA) public data: any){
    this.title = data.title;
    this.message = data.message
  }
  ngOnInit(): void {

  }
  close() {
    this.dialogRef.close();
  }
}
