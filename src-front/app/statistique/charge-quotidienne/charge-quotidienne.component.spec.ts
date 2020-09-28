import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargeQuotidienneComponent } from './charge-quotidienne.component';

describe('ChargeQuotidienneComponent', () => {
  let component: ChargeQuotidienneComponent;
  let fixture: ComponentFixture<ChargeQuotidienneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChargeQuotidienneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChargeQuotidienneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
