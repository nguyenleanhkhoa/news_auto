import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsApprovedComponent } from './news-approved.component';

describe('NewsApprovedComponent', () => {
  let component: NewsApprovedComponent;
  let fixture: ComponentFixture<NewsApprovedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewsApprovedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsApprovedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
