import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyCompanyComponent } from './modify-company.component';

describe('ModifyCompanyComponent', () => {
  let component: ModifyCompanyComponent;
  let fixture: ComponentFixture<ModifyCompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyCompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
