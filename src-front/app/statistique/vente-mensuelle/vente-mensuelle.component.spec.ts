import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VenteMensuelleComponent } from './vente-mensuelle.component';

describe('VenteMensuelleComponent', () => {
  let component: VenteMensuelleComponent;
  let fixture: ComponentFixture<VenteMensuelleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VenteMensuelleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VenteMensuelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
