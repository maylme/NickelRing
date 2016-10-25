import React, { Component } from 'react';

import styles from "./View.css";

export default React.createClass({

  getInitialState(){
    return {
      currentPage: 1,
      maxPage: 4
    }
  },

  componentDidMount(){

  },

  _nextPage(){
    if(this.state.currentPage < this.state.maxPage){
      this.setState({
        currentPage: ++this.state.currentPage
      })
    }
  },

  _prevPage(){
    if(this.state.currentPage > 1){
      this.setState({
        currentPage: --this.state.currentPage
      })
    }
  },

  render() {
    return (
      <div className={styles.view}>
        <div className={styles.imageContainer}>
          <i className="fa fa-chevron-left" aria-hidden="true" onClick={this._prevPage}/>
          <img className={styles.image} src={require("../../assets/page" + this.state.currentPage + ".png")} alt=""/>
          <i className="fa fa-chevron-right" aria-hidden="true" onClick={this._nextPage}/>
        </div>
      </div>
    );
  }
})
