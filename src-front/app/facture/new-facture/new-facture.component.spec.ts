import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFactureComponent } from './new-facture.component';

describe('NewFactureComponent', () => {
  let component: NewFactureComponent;
  let fixture: ComponentFixture<NewFactureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewFactureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFactureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
