import React, { Component } from 'react';
import Sidenav from "./Sidenav/Sidenav";
import Slider from "./Slider/Slider";
import MotionDetector from "./Detector/MotionDetector";
import SliderStore from "../stores/SliderStore";


import "./Home.css";

export default React.createClass({
  getInitialState(){
    return {
      currentPage: 1
    }
  },

  componentDidMount(){
  },

  _left(){
    SliderStore.prevSlide();
  },

  _right(){
    SliderStore.nextSlide();
  },

  render() {
    return (
      <div>
        <MotionDetector left={this._left} right={this._right}/>
        <Slider/>
      </div>
    );
  }
})
