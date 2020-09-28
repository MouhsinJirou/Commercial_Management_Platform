import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAvoirClientComponent } from './new-avoir-client.component';

describe('NewAvoirClientComponent', () => {
  let component: NewAvoirClientComponent;
  let fixture: ComponentFixture<NewAvoirClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewAvoirClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAvoirClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
