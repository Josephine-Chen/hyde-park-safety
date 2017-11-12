import React, {Component} from 'react';
import {Col, Row, Input, Container} from 'react-materialize';
import './styles/Sidebar.css';

class Sidebar extends Component {
  render() {
    return (
      <div>
        <h4 className = 'white-text' id='title'> Safety App</h4>
        <Row id = 'directionInput'>
          <Input className = 'white-text' label="Choose your location..." />
          <Input className = 'white-text' label="Choose your destination..." />
        </Row>
        <Row>
          <Container className = 'white'> Some directions here </Container>
        </Row>
      </div>
    );
  }
}

export default Sidebar;