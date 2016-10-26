import { Component, ApplicationRef} from '@angular/core';
import { Slide } from './slide';
import { SlideService } from './slide.service';
import { OnInit } from '@angular/core';
declare var annyang: any; // for use of annyang in the component
declare var $ : any; //for use of jquery in the component

@Component({
  selector: 'my-app',
  providers: [SlideService],
  templateUrl: "app/app.component.template.html",
  styleUrls: ["app/app.component.style.css"]
})
export class AppComponent implements OnInit{
  title = 'NickelRing';
  selectedSlide: Slide;
  howto: Slide[];
  mic_is_on = false;
  onSelect(slide: Slide): void {
    this.selectedSlide = slide;
  };
  after(): void{
    console.log("after", this.selectedSlide);
    if (this.selectedSlide == undefined){
      var next_index = 0;
    }else{
      var next_index = ((this.selectedSlide.index +1) >= this.howto.length)? 0: (this.selectedSlide.index +1);
    }
    this.selectedSlide = this.howto[next_index];
    this.ref.tick();
  };
  before(): void{
    if (this.selectedSlide == undefined){
      var next_index = this.howto.length - 1;
    }else{
      var next_index = ((this.selectedSlide.index - 1) < 0)? this.howto.length - 1: (this.selectedSlide.index -1);
    }
    this.selectedSlide = this.howto[next_index];
    this.ref.tick();
  };
  constructor(private slideService: SlideService, private ref: ApplicationRef) { };

  getSlides(): void {
    this.slideService.getSlides().then(slides => {
      this.howto = slides;
      this.selectedSlide = slides[0];

    });
  };

  ngOnInit(): void {
    this.getSlides();
  };
  toogleMic(): void{
    this.mic_is_on = !this.mic_is_on;

    if (annyang) {
      if(this.mic_is_on){
          var commands = {
              'before': ()=>{ 
                this.before();
              },
              'next': ()=>{
                this.after();
                //$(".arrow.after").click();
              },
              'help': ()=>{ alert("say 'next' or 'back' to navigate"); }
          };
          annyang.addCommands(commands);
          annyang.start();
      } else {
          annyang.removeCommands();
          annyang.abort();
      }
    }
  }
}