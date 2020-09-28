import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerserClientComponent } from './verser-client.component';

describe('VerserClientComponent', () => {
  let component: VerserClientComponent;
  let fixture: ComponentFixture<VerserClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerserClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerserClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
