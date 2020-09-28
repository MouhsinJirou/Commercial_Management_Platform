import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaiementFournisseurComponent } from './paiement-fournisseur.component';

describe('PaiementFournisseurComponent', () => {
  let component: PaiementFournisseurComponent;
  let fixture: ComponentFixture<PaiementFournisseurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaiementFournisseurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaiementFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
