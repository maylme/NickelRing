import React, { Component } from 'react';

import styles from "./Sidenav.css";

export default React.createClass({
  componentDidMount(){

  },

  render() {
    return (
      <div className={styles.sidenav}>
        <span>sidenav</span>
      </div>
    );
  }
})
