package quenchmarketing.quench_pub_bar_finder;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel Baker on 16/02/2017.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_main);

        String placeLatLng = getIntent().getStringExtra("placeLatLng");
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(placeLatLng);
        while(m.find()) {
            String[] LatLngArray = m.group(1).split(",");
            Double Lat = Double.parseDouble(LatLngArray[0].trim());
            Double Lng = Double.parseDouble(LatLngArray[1].trim());
            mLatLng = new LatLng(Lat, Lng);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(mLatLng).title("Your location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 12.0f));
    }
}



