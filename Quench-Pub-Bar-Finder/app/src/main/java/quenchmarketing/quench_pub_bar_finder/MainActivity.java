package quenchmarketing.quench_pub_bar_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LatLngBounds bounds = new LatLngBounds(
            new LatLng(49.383639452689664, -17.39866406249996),
            new LatLng(59.53530451232491, 8.968523437500039));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        EditText mainSearch = (EditText) findViewById(R.id.mainSearchEditText);
        setSupportActionBar(toolbar);
        mainSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AutoCompleteActivity.class);
        startActivity(intent);
    }
}

