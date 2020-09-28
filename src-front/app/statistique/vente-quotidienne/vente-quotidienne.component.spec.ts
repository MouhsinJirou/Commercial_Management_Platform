import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VenteQuotidienneComponent } from './vente-quotidienne.component';

describe('VenteQuotidienneComponent', () => {
  let component: VenteQuotidienneComponent;
  let fixture: ComponentFixture<VenteQuotidienneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VenteQuotidienneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VenteQuotidienneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
