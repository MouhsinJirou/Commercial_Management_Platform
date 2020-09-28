import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerserFournisseurComponent } from './verser-fournisseur.component';

describe('VerserFournisseurComponent', () => {
  let component: VerserFournisseurComponent;
  let fixture: ComponentFixture<VerserFournisseurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerserFournisseurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerserFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
