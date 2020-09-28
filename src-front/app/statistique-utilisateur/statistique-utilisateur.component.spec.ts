import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatistiqueUtilisateurComponent } from './statistique-utilisateur.component';

describe('StatistiqueUtilisateurComponent', () => {
  let component: StatistiqueUtilisateurComponent;
  let fixture: ComponentFixture<StatistiqueUtilisateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatistiqueUtilisateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatistiqueUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
