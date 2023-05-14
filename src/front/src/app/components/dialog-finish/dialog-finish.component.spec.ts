import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { DialogFinishComponent } from './dialog-finish.component';

fdescribe('DialogFinishComponent', () => {
  let component: DialogFinishComponent;
  let fixture: ComponentFixture<DialogFinishComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [DialogFinishComponent],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} }
      ]
    }).compileComponents();
    
    fixture = TestBed.createComponent(DialogFinishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('Deberia crear el componente', () => {
    expect(component).toBeTruthy();
  });

  it('Deberia inicializar los datos con los parametros obtenidos', () => {
    const data = {
      title: 'Test Title',
      text: 'Test Text',
      correctWord: 'Test Correct Word',
      button: 'Test Button'
    };

    component.data.title = data.title;
    component.data.text = data.text;
    component.data.correctWord = data.correctWord;
    component.data.button = data.button;
    component.ngOnInit();

    expect(component.title).toEqual(data.title);
    expect(component.text).toEqual(data.text);
    expect(component.correctWord).toEqual(data.correctWord);
    expect(component.button).toEqual(data.button);
  });

  xit('Deberia llamar al reload() en el refresh()', () => {
    spyOn(window.location, 'reload');
    component.refresh();
    expect(window.location.reload).toHaveBeenCalled();
  });
});
