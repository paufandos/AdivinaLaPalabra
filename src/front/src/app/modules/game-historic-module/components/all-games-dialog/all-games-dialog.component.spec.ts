import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllGamesDialogComponent } from './all-games-dialog.component';

describe('AllGamesDialogComponent', () => {
  let component: AllGamesDialogComponent;
  let fixture: ComponentFixture<AllGamesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllGamesDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllGamesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
