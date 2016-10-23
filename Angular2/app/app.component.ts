import { Component } from '@angular/core';
import { Slide } from './slide';
import { SlideService } from './slide.service';
import { OnInit } from '@angular/core';

@Component({
  selector: 'my-app',
  providers: [SlideService],
  template: `
    <h1>How to :{{title}}</h1>
    <div class="container-fluid">
      <div class="diapo_container row">
        <a class="col-md-2 arrow" (click)="before(selectedSlide)">
          <img src="/assets/before.svg"/>
        </a>
        <div class="col-md-8" > 
            <div class="img_container" *ngIf="selectedSlide">
              <img src="{{selectedSlide.img}}" />
            </div>
        </div>
        <a class="col-md-2 arrow" (click)="after(selectedSlide)">
          <img src="/assets/after.svg"/>
        </a>
      </div>
      <div class="row">
        <div class="col-xs-12 text-center" *ngIf="selectedSlide && howto">
          <h2>{{selectedSlide.index+1}}/{{howto.length}}</h2>
        </div>
      </div>
    </div>
  `,
  styles: [`
   h1{
    text-align:center;
  }

  .diapo_container {
    height: 70vh;
    width:100%;
  }
  a{
    height: 100%;
    display: flex;
    cursor: pointer;
  }
  a img{
    height: 30%;
    margin: auto; /* eh oui, tout bêtement */
  }
   .img_container{
    border: solid black 3px;
    border-radius: 3px;
    width:100%;

    height:100%;
    line-height: 70vh;
    text-align:center;
  }

  .img_container img{
    max-width:100%;
    max-height:100%;
    display:inline;
  }
  `]
})
export class AppComponent implements OnInit{
  title = 'NickelRing';
  selectedSlide: Slide;
  howto: Slide[];
  onSelect(slide: Slide): void {
    this.selectedSlide = slide;
  };
after(selectedSlide: Slide): void{
    if (selectedSlide == undefined){
      var next_index = 0;
    }else{
      var next_index = ((selectedSlide.index +1) >= this.howto.length)? 0: (selectedSlide.index +1);
    }
    this.selectedSlide = this.howto[next_index];
  };
  before(selectedSlide: Slide): void{
    if (selectedSlide == undefined){
      var next_index = this.howto.length - 1;
    }else{
      var next_index = ((selectedSlide.index - 1) < 0)? this.howto.length - 1: (selectedSlide.index -1);
    }
    this.selectedSlide = this.howto[next_index];
  };
  constructor(private slideService: SlideService) { };

  getSlides(): void {
    this.slideService.getSlides().then(slides => {
      this.howto = slides;
      this.selectedSlide = slides[0];
    });
  };

  ngOnInit(): void {
    this.getSlides();
  }
}