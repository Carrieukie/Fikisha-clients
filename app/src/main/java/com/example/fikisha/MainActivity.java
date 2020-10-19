package com.example.fikisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fikisha.RecyclerAdapter.mainActivityAdapter;
import com.example.fikisha.SharedPrefs.Login;
import com.example.fikisha.Util.FirebaseUtil;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView_Services);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(new mainActivityAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                new Login().clear(getApplicationContext());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ViewOrders(View view) {
        startActivity(new Intent(getApplicationContext(),CartActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUtil.callReference(this);
        FirebaseUtil.attachListener();
    }
}
