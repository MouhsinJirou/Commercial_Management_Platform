import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargeMensuelleComponent } from './charge-mensuelle.component';

describe('ChargeMensuelleComponent', () => {
  let component: ChargeMensuelleComponent;
  let fixture: ComponentFixture<ChargeMensuelleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChargeMensuelleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChargeMensuelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
