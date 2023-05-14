import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-finish',
  templateUrl: './dialog-finish.component.html',
  styleUrls: ['./dialog-finish.component.scss'],
})
export class DialogFinishComponent {

  title="";
  text="";
  correctWord="";
  button="";

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogFinishComponent) {}

  ngOnInit() {
    this.title = this.data.title;
    this.text = this.data.text;
    this.correctWord = this.data.correctWord;
    this.button=this.data.button
  }

  refresh() {
    window.location.reload();
  }
}
