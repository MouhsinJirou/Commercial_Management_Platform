import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaiementClientComponent } from './paiement-client.component';

describe('PaiementClientComponent', () => {
  let component: PaiementClientComponent;
  let fixture: ComponentFixture<PaiementClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaiementClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaiementClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
