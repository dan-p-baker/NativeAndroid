package quenchmarketing.quench_pub_bar_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by Daniel Baker on 16/02/2017.
 */

public class AutoCompleteActivity extends AppCompatActivity implements PlaceSelectionListener {

    private LatLngBounds bounds = new LatLngBounds(
            new LatLng(49.383639452689664, -17.39866406249996),
            new LatLng(59.53530451232491, 8.968523437500039));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button doneButton = (Button) findViewById(R.id.autoCompleteDoneButton);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
            getFragmentManager().findFragmentById(R.id.mainSearchAutoCompleteFragment);
        autocompleteFragment.setHint("Search for a Location");
        autocompleteFragment.setBoundsBias(bounds);
        autocompleteFragment.setOnPlaceSelectedListener(this);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}

    @Override
    public void onPlaceSelected(Place place){
        Log.i("PlacesAutoComplete", "Place Selected: " + place.getName());
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("placeLatLng", place.getLatLng().toString());
        startActivity(intent);
    }

    @Override
    public void onError(Status status) {
        Log.e("PlacesAutoComplete", "onError: Status = " + status.toString());
        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
