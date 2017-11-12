import React, {Component} from "react";
import { withScriptjs, withGoogleMap, GoogleMap, Marker, FusionTablesLayer, DirectionsRenderer } from "react-google-maps";
import {compose, withProps, lifecycle} from "recompose";
import {Col, Row, Input, Container, Collapsible, CollapsibleItem} from 'react-materialize';
import './styles/Sidebar.css';
import _ from "lodash";
const google = window.google;

const { StandaloneSearchBox } = require("react-google-maps/lib/components/places/StandaloneSearchBox");

const PlacesWithStandaloneSearchBox = compose(
  withProps({
    googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyBCBR--gzsRGlGU9cL52ETv3A6yS_BawYE&v=3.exp&libraries=geometry,drawing,places",
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div style={{ height: `400px` }} />,
  }),
  lifecycle({
    componentWillMount() {
      const refs = {}

      this.setState({
        places: [],
        onSearchBoxMounted: ref => {
          refs.searchBox = ref;
        },
        onPlacesChanged: () => {
          const places = refs.searchBox.getPlaces();

          this.setState({
            places,
          });
        },
      })
    },
  }),
  withScriptjs
)(props =>
  <div data-standalone-searchbox="">
    <StandaloneSearchBox
      ref={props.onSearchBoxMounted}
      bounds={props.bounds}
      onPlacesChanged={props.onPlacesChanged}
    >
      <input
        type="text"
        placeholder="Enter a location"
        style={{
          boxSizing: `border-box`,
          border: `1px solid transparent`,
          width: `210px`,
          height: `32px`,
          padding: `0 12px`,
          borderRadius: `3px`,
          boxShadow: `0 2px 6px rgba(0, 0, 0, 0.3)`,
          fontSize: `14px`,
          outline: `none`,
          textOverflow: `ellipses`,
        }}
      />
    </StandaloneSearchBox>
    <ol>
      {props.places.map(({ place_id, formatted_address, geometry: { location } }) =>
        <li key={place_id}>
          {formatted_address}
          {" at "}
          ({location.lat()}, {location.lng()})
        </li>
      )}
    </ol>
  </div>
);

const MapWithADirectionsRenderer = compose(
  withProps({
    googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyBCBR--gzsRGlGU9cL52ETv3A6yS_BawYE&v=3.exp&libraries=geometry,drawing,places",
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div style={{ height: `100vh` }} />,
    mapElement: <div style={{ height: `100%` }} />,
  }),
  withScriptjs,
  withGoogleMap,
  lifecycle({
    componentDidMount() {
      const DirectionsService = new google.maps.DirectionsService();
      DirectionsService.route({
        origin: new google.maps.LatLng(41.795316, -87.591354),
        destination: new google.maps.LatLng(41.790180, -87.599139),
        travelMode: google.maps.TravelMode.WALKING,
      }, (result, status) => {
        if (status === google.maps.DirectionsStatus.OK) {
          this.setState({
            directions: result,
          });
        } else {
          console.error(`error fetching directions ${result}`);
        }
      });
    }
  })
)(props =>
  <GoogleMap
    defaultZoom={15}
    defaultCenter={new google.maps.LatLng(41.8507300, -87.6512600)}
    ref={props.onMapMounted}
    onBoundsChanged={props.onBoundsChanged}
    center={props.center}
  >
    {props.directions && <DirectionsRenderer directions={props.directions} />}
    {props.showBlue && <FusionTablesLayer
      url="http://googlemaps.github.io/js-v2-samples/ggeoxml/cta.kml"
      options={{
        query: {
          select: `Geocodable address`,
          from: `1YO1QdVye6hI08RVkIOP7L5469yDNMLJXFqy3RIjz`
        },
        clickable: false,
        styles: [{markerOptions: {
          iconName: 'measle_turquoise'
        }}]
      }}
    />}
  </GoogleMap>

);

class areaMap extends React.PureComponent {
  state = {
    isMarkerShown: false,
    showBlue: false,
    places: []
  }

  componentWillMount() {
    const refs = {}

    this.setState({
      places: [],
      onSearchBoxMounted: ref => {
        refs.searchBox = ref;
      },
      onPlacesChanged: () => {
        const places = refs.searchBox.getPlaces();

        this.setState({
          places,
        });
      },
    })
  }

  componentDidMount() {
    this.delayedShowMarker()
  }

  delayedShowMarker = () => {
    setTimeout(() => {
      this.setState({ isMarkerShown: true })
    }, 3000)
  }

  setLocations = (locType) => {
    if (locType === 'origin' && this.state.places !== undefined) {
      this.setState({origin: this.state.places[0]});
      console.log('origin is', this.state.origin);
    } else if (this.state.places !==undefined) {
      this.setState({destination: this.state.places[0]});
      console.log('destination is', this.state.destination);
    }
  }

  handleMarkerClick = () => {
    this.setState({ isMarkerShown: false })
    this.delayedShowMarker()
  }

  blueLightsToggle = () => {
    this.setState({showBlue: !this.state.showBlue})
  }
  render() {
    console.log(this.state.origin, this.state.destination);
    return (
      <Row>
        <Col s={2} id = "sidebar" className = 'blue'>
        <h4 className = 'white-text' id='title'> Safety App</h4>
        <Row id = 'directionInput'>
          <h8 className = 'white-text'> Origin</h8>
          <StandaloneSearchBox
            ref={this.state.onSearchBoxMounted}
            bounds={{east: -87.574766, north:41.802272, south:41.780759, west:  -87.616217}}
            onPlacesChanged={this.state.onPlacesChanged}
            locationType={this.setLocations('origin')}
            >
              <input
                type="text"
                placeholder="Enter a location"
                style={{
                  boxSizing: `border-box`,
                  border: `1px solid transparent`,
                  width: `210px`,
                  height: `32px`,
                  padding: `0 12px`,
                  borderRadius: `3px`,
                  boxShadow: `0 2px 6px rgba(0, 0, 0, 0.3)`,
                  fontSize: `14px`,
                  outline: `none`,
                  textOverflow: `ellipses`,
                }}
              />
              </StandaloneSearchBox>
              <h8 className = 'white-text'> Destination</h8>
              <StandaloneSearchBox
                      ref={this.state.onSearchBoxMounted}
                      bounds={{east: -87.574766, north:41.802272, south:41.780759, west:  -87.616217}}
                      onPlacesChanged={this.state.onPlacesChanged}
                      locationType={this.setLocations('destination')}
                      >
                        <input
                          type="text"
                          placeholder="Enter a location"
                          style={{
                            boxSizing: `border-box`,
                            border: `1px solid transparent`,
                            width: `210px`,
                            height: `32px`,
                            padding: `0 12px`,
                            borderRadius: `3px`,
                            boxShadow: `0 2px 6px rgba(0, 0, 0, 0.3)`,
                            fontSize: `14px`,
                            outline: `none`,
                            textOverflow: `ellipses`,
                          }}
                        />
              </StandaloneSearchBox>
        </Row>
        <Row>
        <Collapsible className = 'light-blue lighten-5'>
          <CollapsibleItem header='Filter'>
            <Row>
              <Input name='filter' type='checkbox' value={this.state.showBlue} label='Emergency Phones' onClick={this.blueLightsToggle} />
            </Row>
          </CollapsibleItem>
        </Collapsible>
          <Container className = 'white'> Some directions here </Container>
        </Row>
        </Col>
        <Col s={10}>
          <MapWithADirectionsRenderer showBlue = {this.state.showBlue} origin = {this.state.origin} destination = {this.state.destination} directions = {{geocoded_waypoints: []}}/>
        </Col>
      </Row>

    )
  }
}
//<Input className = 'white-text' label="Choose your location..." />
//<PlacesWithStandaloneSearchBox bounds={{east: -87.574766, north:41.802272, south:41.780759, west:  -87.616217}}/>
//<Input className = 'white-text' label="Choose your destination..." />
//<PlacesWithStandaloneSearchBox bounds={{east: -87.574766, north:41.802272, south:41.780759, west:  -87.616217}}/>

// const { SearchBox } = require("react-google-maps/lib/components/places/SearchBox");

// const MyMapComponent = compose(
//   withProps({
//     googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyBCBR--gzsRGlGU9cL52ETv3A6yS_BawYE&v=3.exp&libraries=geometry,drawing,places",
//     loadingElement: <div style={{ height: `100%` }} />,
//     containerElement: <div style={{ height: `100vh` }} />,
//     mapElement: <div style={{ height: `100%` }} />,
//   }),
//   withScriptjs,
//   withGoogleMap
// )((props) =>
//   <GoogleMap
//     defaultZoom={15}
//     defaultCenter={{ lat: 41.788609, lng: -87.598713 }}
//   >
//     {props.isMarkerShown && <Marker position={{ lat: 41.788609, lng: -87.598713 }} />}
//   </GoogleMap>
// );

// const MapWithADirectionsRenderer = compose(
//   withProps({
//     googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyBCBR--gzsRGlGU9cL52ETv3A6yS_BawYE&v=3.exp&libraries=geometry,drawing,places",
//     loadingElement: <div style={{ height: `100%` }} />,
//     containerElement: <div style={{ height: `100vh` }} />,
//     mapElement: <div style={{ height: `100%` }} />,
//   }),
//   withScriptjs,
//   withGoogleMap,
//   lifecycle({
//     componentWillMount() {
//       const refs = {}

//       this.setState({
//         bounds: {east: -87.574766,
//           north:41.802272,
//           south:41.780759,
//           west:  -87.616217},
//         center: {
//           lat: 41.795316, lng: -87.591354
//         },
//         markers: [],
//         onMapMounted: ref => {
//           refs.map = ref;
//         },
//         onBoundsChanged: () => {
//           this.setState({
//             bounds: refs.map.getBounds(),
//             center: refs.map.getCenter(),
//           })
//         },
//         onSearchBoxMounted: ref => {
//           refs.searchBox = ref;
//         },
//         onPlacesChanged: () => {
//           const places = refs.searchBox.getPlaces();
//           const bounds = new google.maps.LatLngBounds();

//           places.forEach(place => {
//             if (place.geometry.viewport) {
//               bounds.union(place.geometry.viewport)
//             } else {
//               bounds.extend(place.geometry.location)
//             }
//           });
//           const nextMarkers = places.map(place => ({
//             position: place.geometry.location,
//           }));
//           const nextCenter = _.get(nextMarkers, '0.position', this.state.center);

//           this.setState({
//             center: nextCenter,
//             markers: nextMarkers,
//           });
//           // refs.map.fitBounds(bounds);
//         },
//       })
//     },
//   },{
//     componentDidMount() {
//       const DirectionsService = new google.maps.DirectionsService();
//       DirectionsService.route({
//         origin: new google.maps.LatLng(41.795316, -87.591354),
//         destination: new google.maps.LatLng(41.790180, -87.599139),
//         travelMode: google.maps.TravelMode.WALKING,
//       }, (result, status) => {
//         if (status === google.maps.DirectionsStatus.OK) {
//           this.setState({
//             directions: result,
//           });
//         } else {
//           console.error(`error fetching directions ${result}`);
//         }
//       });
//     }
//   })
// )(props =>
//   <GoogleMap
//     defaultZoom={15}
//     defaultCenter={new google.maps.LatLng(41.8507300, -87.6512600)}
//     ref={props.onMapMounted}
//     onBoundsChanged={props.onBoundsChanged}
//     center={props.center}
//   >
//   <SearchBox
//       ref={props.onSearchBoxMounted}
//       bounds={props.bounds}
//       controlPosition={google.maps.ControlPosition.TOP_LEFT}
//       onPlacesChanged={props.onPlacesChanged}
//     >
//     <input
//         type="text"
//         id="originInput"
//         placeholder="Origin"
//         autocomplete="on"
//         style={{
//           backgroundColor: `#fff`,
//           boxSizing: `border-box`,
//           border: `1px solid transparent`,
//           width: `240px`,
//           height: `32px`,
//           marginTop: `27px`,
//           padding: `0 12px`,
//           borderRadius: `3px`,
//           boxShadow: `0 2px 6px rgba(0, 0, 0, 0.3)`,
//           fontSize: `14px`,
//           outline: `none`,
//           textOverflow: `ellipses`
//         }}
//       />
//     </SearchBox>
//     // {props.markers.map((marker, index) =>
//     <Marker key={index} position={marker.position} />
//     )}
//     {props.directions && <DirectionsRenderer directions={props.directions} />}
//     {props.showBlue && <FusionTablesLayer
//       url="http://googlemaps.github.io/js-v2-samples/ggeoxml/cta.kml"
//       options={{
//         query: {
//           select: `Geocodable address`,
//           from: `1YO1QdVye6hI08RVkIOP7L5469yDNMLJXFqy3RIjz`
//         },
//         clickable: false,
//         styles: [{markerOptions: {
//           iconName: 'measle_turquoise'
//         }}]
//       }}
//     />}
//   </GoogleMap>
// );
export default areaMap;