package com.example.dongpeng.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new MyLayoutManager());
        List<String> data=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("");
        }
        MyAdapter adapter=new MyAdapter(data);
        rv.setAdapter(adapter);
        ItemTouchHelper.Callback callback=new SimpleCallback(new ISwipeListener(){
            @Override
            public void selectedItem(int flag, int position) {
                if (flag==0){
                    Toast.makeText(MainActivity.this,"左滑------"+position,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"右滑-------"+position,Toast.LENGTH_SHORT).show();
                }
            }
        },adapter);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);
    }
}
