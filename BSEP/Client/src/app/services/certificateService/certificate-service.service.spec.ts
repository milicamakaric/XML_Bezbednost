import { TestBed } from '@angular/core/testing';

import { CertificateServiceService } from './certificate-service.service';

describe('CertificateServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CertificateServiceService = TestBed.get(CertificateServiceService);
    expect(service).toBeTruthy();
  });
});