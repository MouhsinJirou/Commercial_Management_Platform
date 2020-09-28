import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewBonAchatComponent } from './new-bon-achat.component';

describe('NewBonAchatComponent', () => {
  let component: NewBonAchatComponent;
  let fixture: ComponentFixture<NewBonAchatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewBonAchatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewBonAchatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
