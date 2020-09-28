import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvoirClientComponent } from './avoir-client.component';

describe('AvoirClientComponent', () => {
  let component: AvoirClientComponent;
  let fixture: ComponentFixture<AvoirClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvoirClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvoirClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
