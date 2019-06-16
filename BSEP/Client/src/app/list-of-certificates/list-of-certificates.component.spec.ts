import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfCertificatesComponent } from './list-of-certificates.component';

describe('ListOfCertificatesComponent', () => {
  let component: ListOfCertificatesComponent;
  let fixture: ComponentFixture<ListOfCertificatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOfCertificatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfCertificatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
