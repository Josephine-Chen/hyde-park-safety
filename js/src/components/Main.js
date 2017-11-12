import React, { Component } from 'react';
import {Row, Col} from 'react-materialize';
import Map from './Map.js';
import RightBar from './RightBar.js';

class Main extends Component {
  render() {
    return (
      <Row>
        <Map/>
        <RightBar/>
      </Row>
    );
  }
}

export default Main;
