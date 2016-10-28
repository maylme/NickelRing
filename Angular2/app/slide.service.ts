import { Injectable } from '@angular/core';
import { NickelRing } from './mock-NickelRing';
import { Slide } from './slide';

@Injectable()
export class SlideService {

	getSlides(): Promise<Slide[]> {
	  return Promise.resolve(NickelRing);
	}

}