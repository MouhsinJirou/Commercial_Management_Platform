import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchatMensuelleComponent } from './achat-mensuelle.component';

describe('AchatMensuelleComponent', () => {
  let component: AchatMensuelleComponent;
  let fixture: ComponentFixture<AchatMensuelleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchatMensuelleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchatMensuelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
