/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { WebsourceService } from './WebSource.service';

describe('Service: WebSource', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WebsourceService]
    });
  });

  it('should ...', inject([WebsourceService], (service: WebsourceService) => {
    expect(service).toBeTruthy();
  }));
});
