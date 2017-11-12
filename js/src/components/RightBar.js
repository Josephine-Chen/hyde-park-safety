import React, { Component } from 'react';
import {SideNav, SideNavItem, Button, Icon, Container} from 'react-materialize';
import './styles/RightBar.css';

class RightBar extends Component {
  render() {
    return (
      <SideNav
        trigger={<Button floating large className = 'blue' waves='light' icon='add' id='more'></Button>}
        options={{ closeOnClick: true , edge: 'right'}}
        >

        <SideNavItem subheader><h4 className = 'blue-text' id='title'>Resources</h4></SideNavItem>
        <SideNavItem subheader>UChicago Police Department</SideNavItem>
        <SideNavItem subheader icon="phone" id='phonenum'>773-702-8181</SideNavItem>
        <SideNavItem subheader>On-Call University Administrator:</SideNavItem>
        <SideNavItem subheader icon="phone" id='phonenum'>773-834-4357</SideNavItem>

        <SideNavItem divider />        
        <SideNavItem subheader>Additional Information</SideNavItem>
        <SideNavItem waves href="http://safety-security.uchicago.edu/" target="_blank" icon='computer'>UChicago Safety & Security</SideNavItem>
        <SideNavItem waves href="https://safety-security.uchicago.edu/emergency_management/calert_system/" target="_blank" icon='web'>Sign Up For cAlert</SideNavItem>
        <SideNavItem waves href="http://safety-security.uchicago.edu/transportation/" target="_blank" icon='directions_bus'>UGo Shuttles</SideNavItem>

      </SideNav>


    );
  }
}

export default RightBar;
