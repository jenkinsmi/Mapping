package com.example.a2jenkm59.mapping;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

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

    public void onStart()
    {
        super.onStart();
        new AlertDialog.Builder(this).setPositiveButton("OK", null).
                setMessage("onStart()").show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );

    }

    public void onStop()
    {
        super.onStop();
        Toast.makeText(this, "onStop()", Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        else if (item.getItemId() == R.id.preferences)
        {
            Intent intent = new Intent (this,PreferencesActivity.class);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.setlocation)
        {
            Intent intent = new Intent (this,setLocationActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        return false;
    }
    public void onClick(View view)
    {

        EditText lattext = (EditText)findViewById(R.id.et1);
        EditText longtext = (EditText)findViewById(R.id.et2);
        double latitude = Double.parseDouble(lattext.getText().toString());
        double longitude = Double.parseDouble(longtext.getText().toString());
        mv.getController().setCenter(new GeoPoint(latitude,longitude));
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.a2jenkm59.mapping.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                double latitude = extras.getDouble("com.example.a2jenkm59.mapping.latitude");
                double longitude = extras.getDouble("com.example.a2jenkm59.mapping.longitude");
                mv.getController().setCenter(new GeoPoint(latitude,longitude));
            }
        }
    }

}