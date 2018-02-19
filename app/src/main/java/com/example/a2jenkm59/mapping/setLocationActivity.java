package com.example.a2jenkm59.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class setLocationActivity extends Activity implements View.OnClickListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        Button regular = (Button)findViewById(R.id.buttonSetLayout);
        regular.setOnClickListener(this);

    }
    public void onClick(View view)
    {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        EditText lattext = (EditText)findViewById(R.id.et1);
        EditText longtext = (EditText)findViewById(R.id.et2);
        double latitude = Double.parseDouble(lattext.getText().toString());
        double longitude = Double.parseDouble(longtext.getText().toString());

        bundle.putDouble("com.example.a2jenkm59.mapping.latitude",latitude);
        bundle.putDouble("com.example.a2jenkm59.mapping.longitude",longitude);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
