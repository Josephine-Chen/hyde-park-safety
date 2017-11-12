import React from "react";
import { withScriptjs, withGoogleMap, GoogleMap, Marker, FusionTablesLayer, DirectionsRenderer } from "react-google-maps";
import {compose, withProps, lifecycle} from "recompose";
const google = window.google;

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
    defaultZoom={7}
    defaultCenter={new google.maps.LatLng(41.8507300, -87.6512600)}
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

const MapWithAFusionTablesLayer = compose(
  withProps({
    googleMapURL: "https://maps.googleapis.com/maps/api/js?key=AIzaSyBCBR--gzsRGlGU9cL52ETv3A6yS_BawYE&v=3.exp&libraries=geometry,drawing,places",
    loadingElement: <div style={{ height: `100%` }} />,
    containerElement: <div style={{ height: `100vh` }} />,
    mapElement: <div style={{ height: `100%` }} />,
  }),
  withScriptjs,
  withGoogleMap
)(props =>
  <GoogleMap
    defaultZoom={15}
    defaultCenter={{ lat: 41.788609, lng: -87.598713  }}
  >
    <FusionTablesLayer
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
    />
  </GoogleMap>
);



class areaMap extends React.PureComponent {
  state = {
    isMarkerShown: false,
    showBlue: false,
  }

  componentDidMount() {
    this.delayedShowMarker()
  }

  delayedShowMarker = () => {
    setTimeout(() => {
      this.setState({ isMarkerShown: true })
    }, 3000)
  }

  handleMarkerClick = () => {
    this.setState({ isMarkerShown: false })
    this.delayedShowMarker()
  }

  render() {
    return (
      <MapWithADirectionsRenderer />
    )
  }
}

export default areaMap;