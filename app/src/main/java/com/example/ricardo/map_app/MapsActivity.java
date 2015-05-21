package com.example.ricardo.map_app;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ricardo.poi.SkatePark;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private HashMap<String,String> SkateParkIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        SkateParkIds = new HashMap<String,String>();



        /* */
        Location location = new Location("initial location");
        location.setLatitude(38.810149);
        location.setLongitude(-9.332124);


       SkatePark skatePark = new SkatePark();
       skatePark.setLocation(location);
       skatePark.setMarkerText("It's my house nigga!!");
       skatePark.setSnippet(
               "O parque abre 10:00 e fecha às 17:00.\n" +
               "Neste momento está fechado para férias.\n"+
               "O parque está danificado e não se pode andar."
       );

        CenterMap(skatePark.getLocation());
//      PlaceMarker(location);
        PlaceMarker(skatePark);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(SkateParkIds.containsKey(marker.getId())){

                    String markerType = SkateParkIds.get(marker.getId());
                    if(markerType == "skatepark"){
                        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.poiDetailsRelativeLayout);
                        relativeLayout.setVisibility(View.VISIBLE);
                        //Lock map
                        mMap.getUiSettings().setScrollGesturesEnabled(false);
                    }
                }

                return false;
            }
        });
    }

    private void PlaceMarker(SkatePark skatePark) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(skatePark.getLocation().getLatitude(), skatePark.getLocation().getLongitude()))
                        .title(skatePark.getMarkerText())
                        .snippet(skatePark.getSnippet())
                        .draggable(true)
        );

        SkateParkIds.put(marker.getId(), "skatepark");
    }


    private void PlaceMarker(Location location) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Hello world"));
    }




    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.

            mMap=((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void CenterMap(Location location){
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        float zoom = 12;//mMap.getMaxZoomLevel();
        float tilt = 0;
        float bearing = 0;
        CameraPosition cameraPosition = new CameraPosition(position,zoom,tilt,bearing );

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        mMap.moveCamera(cameraUpdate);
    }
}
