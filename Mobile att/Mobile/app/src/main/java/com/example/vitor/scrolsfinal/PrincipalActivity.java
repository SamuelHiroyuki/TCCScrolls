package com.example.vitor.scrolsfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class PrincipalActivity extends AppCompatActivity  {
        CarouselPicker carouselPicker;
        DrawerLayout drawer;
        AdapterCat adapter;
        AdapterProd prodAdapter1, prodAdapter2;
        LinearLayout HeaderLayput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar mTopToolbar = findViewById(R.id.IncludeToolbarPrincipal);
        setSupportActionBar(mTopToolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.itmComprar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.itmConfig:
                        Intent intent = new Intent(getApplicationContext(), Principal.class);
                        startActivity(intent);
                        break;
                    case R.id.itmMapa:
                        Intent intent2 = new Intent(getApplicationContext(), Principal.class);
                        startActivity(intent2);
                        break;
                    case R.id.itmMeusPedidos:
                        Intent intent3 = new Intent(getApplicationContext(), Principal.class);
                        startActivity(intent3);
                        break;
                    case R.id.itmQRCam:
                        Intent intent4 = new Intent(getApplicationContext(), Principal.class);
                        startActivity(intent4);
                        break;
                }
                return true;
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,mTopToolbar,R.string.Drawer_open,R.string.Drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        HeaderLayput = (LinearLayout) findViewById(R.id.HeaderLayout);

        List<CarouselPicker.PickerItem> imgsBanner = new ArrayList<>();
        imgsBanner.add(new CarouselPicker.DrawableItem(R.drawable.banner_cyber));
        imgsBanner.add(new CarouselPicker.DrawableItem(R.drawable.bookbg));
        imgsBanner.add(new CarouselPicker.DrawableItem(R.drawable.book_true_icon));
        CarouselPicker.CarouselViewAdapter imgAdapter = new CarouselPicker.CarouselViewAdapter(this,imgsBanner,0);
        CarouselPicker crr = (CarouselPicker) findViewById(R.id.Carrsky);
        crr.setAdapter(imgAdapter);

        ArrayList<Integer> imagens = new ArrayList<>();
        imagens.add(R.drawable.banner_cyber);
        imagens.add(R.drawable.book_true_icon);
        imagens.add(R.drawable.bookbg);
        imagens.add(R.drawable.bookicon);

        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Fantasia");
        nomes.add("Suspense");
        nomes.add("Ficção");
        nomes.add("Guias");

        ArrayList<String> precos = new ArrayList<>();
        precos.add("15");
        precos.add("69");
        precos.add("21");
        precos.add("02");

        RecyclerView recyclerView = findViewById(R.id.rvCategorias);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new AdapterCat(this, imagens,nomes);

        recyclerView.setAdapter(adapter);


        RecyclerView rv1 = findViewById(R.id.rvNovo);
        LinearLayoutManager VerticalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv1.setLayoutManager(VerticalLayoutManager);
        prodAdapter1 = new AdapterProd(this,imagens,nomes,precos);

        rv1.setAdapter(prodAdapter1);

        RecyclerView rv2 = findViewById(R.id.rvAvaliado);
        LinearLayoutManager verticalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv2.setLayoutManager(verticalLayoutManager);


        prodAdapter2 = new AdapterProd(this,imagens,nomes,precos);

        rv2.setAdapter(prodAdapter2);

    }
    @Override
    public void onBackPressed(){

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }



}
