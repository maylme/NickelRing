export class Diapo{
  index: number;
  img: string;
}

const NyckelRing: Diapo[] = [
  { index: 0, img: '/assets/NyckelRing/page1.png' },
  { index: 1, img: '/assets/NyckelRing/page2.png' },
  { index: 2, img: '/assets/NyckelRing/page3.png' },
  { index: 3, img: '/assets/NyckelRing/page4.png' },
];

import { Component } from '@angular/core';
@Component({
  selector: 'my-app',
  template:`
    <h1>How to : {{title}}</h1>
    <div class="container-fluid">
      <div class="diapo_container row">
        <a class="col-md-2 arrow" (click)="before(selectedDiapo)">
          <img src="/assets/before.svg"/>
        </a>
        <div class="col-md-8" > 
          <div class="img_container" *ngIf="selectedDiapo">
              <img src="{{selectedDiapo.img}}" />
          </div>  
        </div>
        <a class="col-md-2 arrow" (click)="after(selectedDiapo)">
          <img src="/assets/after.svg"/>
        </a>
      </div>
      <div class="row">
        <div class="col-xs-12 text-center">
          <h2>{{selectedDiapo.index + 1}}/{{tuto.length}}</h2>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">

        </div>
        <div class="col-md-6">
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
    background:red;
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
    height: 100%;
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
export class AppComponent { 
  title = 'NyckelRing';
  tuto = NyckelRing;
  selectedDiapo= this.tuto[0];
  after(selectedDiapo: Diapo): void{
    if (selectedDiapo == undefined){
      var next_index = 0;
    }else{
      var next_index = ((selectedDiapo.index +1) >= this.tuto.length)? 0: (selectedDiapo.index +1);
    }
    this.selectedDiapo = this.tuto[next_index];
  };
  before(selectedDiapo: Diapo): void{
    if (selectedDiapo == undefined){
      var next_index = this.tuto.length - 1;
    }else{
      var next_index = ((selectedDiapo.index - 1) < 0)? this.tuto.length - 1: (selectedDiapo.index -1);
    }
    this.selectedDiapo = this.tuto[next_index];
  }
}
