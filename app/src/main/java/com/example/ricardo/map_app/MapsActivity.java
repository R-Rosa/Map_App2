package com.example.ricardo.map_app;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private HashMap<String,SkatePark> SkateParkIds;

    RelativeLayout relativeLayout=null;
    ImageView closeButton=null;
    ImageView backgroundWindow = null;
    TextView skateParkTitle = null;
    ImageView skateParkPhotoImageView = null;
    TextView parkDescriptionTitle = null;
    TextView parkDescriptionTextView = null;
    TextView parkDirectionsTitleTextView = null;
    TextView parkDirectionsTextView = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        SkateParkIds = new HashMap<String,SkatePark>();


        InitializeDetailedWindowViews();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.INVISIBLE);
                mMap.getUiSettings().setScrollGesturesEnabled(true);

            }
        });


        /* */
        Location location = new Location("initial location");
        location.setLatitude(38.810149);
        location.setLongitude(-9.332124);

        Location liriosLocation = new Location("Lirios Location");
        liriosLocation.setLatitude(38.810);
        liriosLocation.setLongitude(-9.33);

        final SkatePark skatePark = new SkatePark(location, null, "Ski Skate Amadora Parque", "Este parque é bué da fixe.",
                "Condicoes do Park", "Como chegar...", "Ao pé do Continente da Amadora");

        SkatePark lirios = new SkatePark(liriosLocation, null, "Skate Park dos Lírios", "Este parque é ainda melhor.",
        "Condicoes do Park", "Como Chegar...", "Ao pé do chaby.");

        CenterMap(skatePark.getLocation());
        PlaceMarker(skatePark);
        PlaceMarker(lirios);




        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(SkateParkIds.containsKey(marker.getId())){

                    SkatePark skatePark = SkateParkIds.get(marker.getId());
                    UpdateDetailsWindow(skatePark);
                    relativeLayout.setVisibility(View.VISIBLE);
                    //Lock map
                    mMap.getUiSettings().setScrollGesturesEnabled(false);
                }

                return false;
            }
        });
    }

    public void InitializeDetailedWindowViews() {
        relativeLayout = (RelativeLayout)findViewById(R.id.poiDetailsRelativeLayout);
        closeButton = (ImageView)findViewById(R.id.closeButton);
        backgroundWindow = (ImageView)findViewById(R.id.backgroundImageView);
        skateParkTitle = (TextView)findViewById(R.id.skateParkTitle);
        skateParkPhotoImageView = (ImageView)findViewById(R.id.skateParkPhotoImageView);
        parkDescriptionTextView = (TextView)findViewById(R.id.parkDescriptionTextView);
        parkDescriptionTitle = (TextView)findViewById(R.id.parkDescriptionTitle);
        parkDirectionsTitleTextView = (TextView)findViewById(R.id.parkDescriptionTitle);
        parkDirectionsTextView = (TextView)findViewById(R.id.parkDirectionsTextView);
    }

    private void PlaceMarker(SkatePark skatePark) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(skatePark.getLocation().getLatitude(), skatePark.getLocation().getLongitude()))
                        .draggable(true)
        );

        SkateParkIds.put(marker.getId(), skatePark);
    }

    private void UpdateDetailsWindow(SkatePark skatePark)
    {
        skateParkTitle.setText(skatePark.getSkateParkTitle());
        if (skatePark.getSkateParkPhoto()!= null)
        {
            skateParkPhotoImageView.setImageBitmap(skatePark.getSkateParkPhoto());
        }
        else{
            //to do - add default image when it doesn't exist (null)
        }

        skateParkTitle.setText(skatePark.getSkateParkTitle());
        parkDescriptionTextView.setText(skatePark.getSkateParkTitle());
        parkDescriptionTitle.setText(skatePark.getParkDescription());
        parkDirectionsTitleTextView.setText(skatePark.getParkDescriptionTitle());
        parkDirectionsTextView.setText(skatePark.getParkDirections());



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
