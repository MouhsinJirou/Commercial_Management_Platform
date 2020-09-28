import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListbonAchatsComponent } from './listbon-achats.component';

describe('ListbonAchatsComponent', () => {
  let component: ListbonAchatsComponent;
  let fixture: ComponentFixture<ListbonAchatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListbonAchatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListbonAchatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
