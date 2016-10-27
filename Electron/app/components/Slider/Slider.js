import React, { Component } from 'react';
import SliderStore from "../../stores/SliderStore";

import "../../libs/foundation.min.css";
import styles from "./Slider.css";

export default React.createClass({
  getInitialState(){
    return {
      currentPage: 1,
      maxPage: 4,
      motionDetection: false
    }
  },

  componentDidMount() {
    this.subscription = SliderStore.data$.subscribe((data) => {
      if(typeof data.motionDetection != "undefined"){
        this.setState({
          motionDetection: data.motionDetection
        });
      }

      switch (data.action) {
        case "prev":
          if (this.state.currentPage > 1 && this.state.motionDetection) {
            this.setState({
              currentPage: this.state.currentPage - 1
            });
          }
          break;
        case "next":
          if (this.state.currentPage < this.state.maxPage && this.state.motionDetection) {
            this.setState({
              currentPage: this.state.currentPage + 1
            });
          }
          break;
        default:
          break;
      }
    });
  },

  componentWillUnmount() {
    this.subscription.dispose();
  },


  _nextPage(){
    if (this.state.currentPage < this.state.maxPage) {
      this.setState({
        currentPage: this.state.currentPage + 1
      });
      SliderStore.nextSlide();
    }
  },

  _prevPage(){
    if (this.state.currentPage > 1) {
      this.setState({
        currentPage: this.state.currentPage - 1
      });
      SliderStore.prevSlide();
    }
  },

  _enableDisable(){
    SliderStore.motionDetection();
  },

  render() {
    return (
      <div className={styles.view}>
        <div className={styles.imageContainer}>
          <i className="fa fa-chevron-left" aria-hidden="true" onClick={this._prevPage}/>
          <img className={styles.image} src={require("../../assets/page" + this.state.currentPage + ".png")} alt=""/>
          <i className="fa fa-chevron-right" aria-hidden="true" onClick={this._nextPage}/><br/>
          <span style={{fontSize: "14px"}}>{this.state.currentPage + " / " + this.state.maxPage}</span>
          <div>
            <label className={styles.switch}>
              <input type="checkbox" onChange={() => {this._enableDisable()}}/>
              <div className={styles.slider}></div>
            </label><span style={{fontSize: "18px", lineHeight:"35px", verticalAlign:"top"}}> Motion detection</span>
          </div>

        </div>
      </div>
    );
  }
})
