package com.example.a2jenkm59.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MapChooseActivity extends Activity implements View.OnClickListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose);

        Button regular = (Button)findViewById(R.id.btnRegular);
        Button hikebikemap = (Button)findViewById(R.id.btnHikeBikeMap);
        regular.setOnClickListener(this);
        hikebikemap.setOnClickListener(this);
    }
    public void onClick(View view)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean hikebikemap=false;
        if (view.getId()==R.id.btnHikeBikeMap)
        {
            hikebikemap=true;
        }
        bundle.putBoolean("com.example.a2jenkm59.mapping.hikebikemap",hikebikemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}