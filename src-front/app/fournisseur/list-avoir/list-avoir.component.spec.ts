import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAvoirComponent } from './list-avoir.component';

describe('ListAvoirComponent', () => {
  let component: ListAvoirComponent;
  let fixture: ComponentFixture<ListAvoirComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListAvoirComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListAvoirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
