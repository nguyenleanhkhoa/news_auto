/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { WebformatService } from './webformat.service';

describe('Service: Webformat', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WebformatService]
    });
  });

  it('should ...', inject([WebformatService], (service: WebformatService) => {
    expect(service).toBeTruthy();
  }));
});
