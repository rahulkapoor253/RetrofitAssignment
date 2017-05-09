package com.skeleton.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.skeleton.R;
import com.skeleton.adapter.MyAdapter;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;

import java.util.List;

import model.Example;

/**
 * Created by rahulkapoor on 04/05/17.
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private Button mButton;

    /**
     * @param savedInstanceState current instance is saved;
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mButton = (Button) findViewById(R.id.btn_click);

        RestClient.getApiInterface().getUserData().enqueue(new ResponseResolver<List<Example>>(this, true) {
            /**
             *
             * @param examples examples
             */
            @Override
            public void success(final List<Example> examples) {

                adapter = new MyAdapter(MainActivity.this, examples, 1);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            /**
             *
             * @param error the error
             */
            @Override
            public void failure(final APIError error) {


            }
        });


    }


}
