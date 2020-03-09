package com.midnightgeek.falldetect;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midnightgeek.falldetect.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    ActivityMainBinding mainBinding;
    private Adapter adapter;
    private ListViewModel listViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initData();
    }

    private void initData(){
        listViewModel= ViewModelProviders.of(this).get(ListViewModel.class);

        adapter = new Adapter(this,listViewModel);
        mainBinding.rv.setVisibility(View.VISIBLE);
        mainBinding.rv.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rv.setAdapter(adapter);


        listViewModel.init();
        listViewModel.getFallList().observe(this, fallInfo -> {
            List<ModelFall> data=fallInfo;
            if (data!=null) {
                ArrayList<ModelFall> temp = new ArrayList<>();
                temp.addAll(data);
                adapter.updateAll(temp);
            }
        });
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }
}
