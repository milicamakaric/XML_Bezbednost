import { TestBed } from '@angular/core/testing';

import { SoftwareServiceService } from './software-service.service';

describe('SoftwareServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SoftwareServiceService = TestBed.get(SoftwareServiceService);
    expect(service).toBeTruthy();
  });
});
