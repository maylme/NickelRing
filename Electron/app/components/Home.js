import React, { Component } from 'react';
import Sidenav from "./Sidenav/Sidenav";
import View from "./View/View";

import "./Home.css";

export default React.createClass({
  componentDidMount(){

  },

  render() {
    return (
      <div>
        <Sidenav/>
        <View/>
      </div>
    );
  }
})
