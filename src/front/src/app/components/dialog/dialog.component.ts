import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss'],
})
export class DialogComponent {
  text = '';
  createButton = true;
  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogComponent) {}

  ngOnInit() {
    this.text = this.data.text;
    this.createButton = this.data.createButton;
  }

  refresh() {
    window.location.reload();
  }
}
