import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFournisseurComponent } from './new-fournisseur.component';

describe('NewFournisseurComponent', () => {
  let component: NewFournisseurComponent;
  let fixture: ComponentFixture<NewFournisseurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewFournisseurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
