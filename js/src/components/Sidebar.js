import React, {Component} from 'react';
import {Col, Row, Input} from 'react-materialize';
import './styles/Sidebar.css';

class Sidebar extends Component {
  render() {
    return (
      <Col id = 'sidebar'>
        <h4 className = 'white-text' id='title'> Safety App</h4>
        <Row id = 'directionInput'>
          <Input className = 'white-text' label="Choose your location..." />
          <Input className = 'white-text' label="Choose your destination..." />
        </Row>
        <Row>
          <p> Some directions here </p>
        </Row>
      </Col>
    );
  }
}

export default Sidebar;