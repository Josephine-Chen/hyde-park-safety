import React, { Component } from 'react';
import {Row, Col} from 'react-materialize';
import Sidebar from './Sidebar.js';
import Map from './Map.js';
import RightBar from './RightBar.js';
import './styles/Main.css';

class Main extends Component {
  render() {
    return (
      <Row>
        <Col s={2} id = "sidebar" className = 'blue'>
            <Sidebar />
        </Col>
        <Col s={10}>
          <Map />
        </Col>
        <RightBar/>
      </Row>
    );
  }
}

export default Main;
