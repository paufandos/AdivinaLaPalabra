import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogComponent } from './dialog.component';
import {
  MatDialogModule,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';

fdescribe('DialogComponent', () => {
  let component: DialogComponent;
  let fixture: ComponentFixture<DialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [DialogComponent],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(DialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('Se deberia crear', () => {
    expect(component).toBeTruthy();
  });

  it('Deberia inicializar los datos con los parametros obtenidos', () => {
    const data = {
      text: 'Test Text',
      createButton: true,
    };

    component.data.text = data.text;
    component.data.createButton = data.createButton;
    component.ngOnInit();

    expect(component.text).toEqual(data.text);
    expect(component.createButton).toEqual(data.createButton);
  });

  xit('Deberia llamar al reload() en el refresh()', () => {
    spyOn(window.location, 'reload');
    component.refresh();
    expect(window.location.reload).toHaveBeenCalled();
  });
});
