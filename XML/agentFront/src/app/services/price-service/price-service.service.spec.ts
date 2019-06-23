import { TestBed } from '@angular/core/testing';

import { PriceServiceService } from './price-service.service';

describe('PriceServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PriceServiceService = TestBed.get(PriceServiceService);
    expect(service).toBeTruthy();
  });
});
