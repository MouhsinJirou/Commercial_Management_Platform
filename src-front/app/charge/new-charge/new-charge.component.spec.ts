import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewChargeComponent } from './new-charge.component';

describe('NewChargeComponent', () => {
  let component: NewChargeComponent;
  let fixture: ComponentFixture<NewChargeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewChargeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewChargeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
