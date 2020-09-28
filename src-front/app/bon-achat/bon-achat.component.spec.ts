import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BonAchatComponent } from './bon-achat.component';

describe('BonAchatComponent', () => {
  let component: BonAchatComponent;
  let fixture: ComponentFixture<BonAchatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BonAchatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BonAchatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
