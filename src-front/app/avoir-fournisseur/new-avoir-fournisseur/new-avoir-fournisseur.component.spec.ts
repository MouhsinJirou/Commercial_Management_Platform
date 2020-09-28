import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAvoirFournisseurComponent } from './new-avoir-fournisseur.component';

describe('NewAvoirFournisseurComponent', () => {
  let component: NewAvoirFournisseurComponent;
  let fixture: ComponentFixture<NewAvoirFournisseurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewAvoirFournisseurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAvoirFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
