import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchatQuotidienneComponent } from './achat-quotidienne.component';

describe('AchatQuotidienneComponent', () => {
  let component: AchatQuotidienneComponent;
  let fixture: ComponentFixture<AchatQuotidienneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchatQuotidienneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchatQuotidienneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
