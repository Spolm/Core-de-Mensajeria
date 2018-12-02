import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoreFiltersComponent } from './more-filters.component';

describe('MoreFiltersComponent', () => {
  let component: MoreFiltersComponent;
  let fixture: ComponentFixture<MoreFiltersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoreFiltersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoreFiltersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
