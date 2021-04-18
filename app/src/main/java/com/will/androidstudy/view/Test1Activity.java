package com.will.androidstudy.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.will.androidstudy.R;
import com.will.androidstudy.bean.User;
import com.will.androidstudy.databinding.ActivityTest1Binding;

public class Test1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTest1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_test1);
        User user = new User("Test", "User");
        binding.setUser(user);
    }

}
