import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeAvoirComponent } from './liste-avoir.component';

describe('ListeAvoirComponent', () => {
  let component: ListeAvoirComponent;
  let fixture: ComponentFixture<ListeAvoirComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeAvoirComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeAvoirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
