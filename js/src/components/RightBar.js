import React, { Component } from 'react';
import {SideNav, SideNavItem, Button} from 'react-materialize';

class RightBar extends Component {
  render() {
    return (
      <SideNav
        trigger={<Button floating large className = 'blue' waves='light' icon='add'>SIDE NAV DEMO</Button>}
        options={{ closeOnClick: true , edge: 'right'}}
        >
        <SideNavItem href='#!icon' icon='cloud'>First Link With Icon</SideNavItem>
        <SideNavItem href='#!second'>Second Link</SideNavItem>
        <SideNavItem divider />
        <SideNavItem subheader>Subheader</SideNavItem>
        <SideNavItem waves href='#!third'>Third Link With Waves</SideNavItem>
      </SideNav>
    );
  }
}

export default RightBar;
