// @flow
import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { Router, hashHistory } from 'react-router';
import { syncHistoryWithStore } from 'react-router-redux';

import Home from "./components/Home";

import "./core.css";

console.log("ok");

render(<Home/>, document.getElementById('root'));
