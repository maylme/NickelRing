import React, { Component } from 'react';
import SliderStore from "../../stores/SliderStore";

import "../../libs/foundation.min.css";
import styles from "./Slider.css";

export default React.createClass({
  getInitialState(){
    return {
      currentPage: 1,
      maxPage: 4
    }
  },

  componentDidMount() {
    this.subscription = SliderStore.data$.subscribe((data) => {
      this.setState({
        currentPage: data.currentPage
      })
    });
  },

  componentWillUnmount() {
    this.subscription.dispose();
  },


  _nextPage(){
    if(this.state.currentPage < this.state.maxPage) {
      this.setState({
        currentPage: this.state.currentPage + 1
      });
      SliderStore.nextSlide();
    }
  },

  _prevPage(){
    if(this.state.currentPage > 1) {
      this.setState({
        currentPage: this.state.currentPage - 1
      });
      SliderStore.prevSlide();
    }
  },

  render() {
    return (
      <div className={styles.view}>
        <div className={styles.imageContainer}>
          <i className="fa fa-chevron-left" aria-hidden="true" onClick={this._prevPage}/>
          <img className={styles.image} src={require("../../assets/page" + this.state.currentPage + ".png")} alt=""/>
          <i className="fa fa-chevron-right" aria-hidden="true" onClick={this._nextPage}/>
          <div>
            <label className={styles.switch}>
              <input type="checkbox"/>
              <div className={styles.slider}></div>
            </label>
          </div>


        </div>
      </div>
    );
  }
})
