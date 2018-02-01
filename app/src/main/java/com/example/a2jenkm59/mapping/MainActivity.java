package com.example.a2jenkm59.mapping;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity implements OnClickListener
{

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(50.913639,-1.411781));
        Button b = (Button)findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }
    public void onClick(View view)
    {

        EditText lattext = (EditText)findViewById(R.id.et1);
        EditText longtext = (EditText)findViewById(R.id.et2);
        double latitude = Double.parseDouble(lattext.getText().toString());
        double longitude = Double.parseDouble(longtext.getText().toString());
        mv.getController().setCenter(new GeoPoint(latitude,longitude));
    }

}