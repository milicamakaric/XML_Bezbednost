import { TestBed } from '@angular/core/testing';

import { AgentServiceService } from './agent-service.service';

describe('AgentServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AgentServiceService = TestBed.get(AgentServiceService);
    expect(service).toBeTruthy();
  });
});
