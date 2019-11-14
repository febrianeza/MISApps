package com.kelompok3.misapps.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.kelompok3.misapps.Fragments.AboutFragment;
import com.kelompok3.misapps.Fragments.Galeri_Foto;
import com.kelompok3.misapps.Fragments.Galery_Video;
import com.kelompok3.misapps.Fragments.HomeFragment;
import com.kelompok3.misapps.Fragments.KaryawanFragment;
import com.kelompok3.misapps.Fragments.OfficeFragment;
import com.kelompok3.misapps.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    Drawer result;
    AccountHeader headerResult;

    final Fragment aboutFragment = new AboutFragment();
    final Fragment homeFragment = new HomeFragment();
    final Fragment karyawanFragment = new KaryawanFragment();
    final Fragment officeFragment = new OfficeFragment();
    final Fragment GaleryVideo = new Galery_Video();
    final Fragment GaleriFoto = new Galeri_Foto();

    FragmentManager fragmentManager = getSupportFragmentManager();

    Fragment active = homeFragment;

    String full_name, email, cell_phone;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 3000);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        full_name = getIntent().getStringExtra("full_name");
        email = getIntent().getStringExtra("email");
        cell_phone = getIntent().getStringExtra("cell_phone");

        fragmentManager.beginTransaction().add(R.id.frameLayout, aboutFragment, aboutFragment.getClass().getSimpleName()).hide(aboutFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, GaleryVideo, GaleryVideo.getClass().getSimpleName()).hide(GaleryVideo).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, GaleriFoto, GaleriFoto.getClass().getSimpleName()).hide(GaleriFoto).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, karyawanFragment, karyawanFragment.getClass().getSimpleName()).hide(karyawanFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, officeFragment, officeFragment.getClass().getSimpleName()).hide(officeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, homeFragment, homeFragment.getClass().getSimpleName()).commit();

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(new ProfileDrawerItem().withName(full_name).withEmail(email).withIcon(getResources().getDrawable(R.drawable.woman)))
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_home).withSelectable(true),
                        new PrimaryDrawerItem().withName("About").withIcon(GoogleMaterial.Icon.gmd_info).withSelectable(true),
                        new PrimaryDrawerItem().withName("Daftar Karyawan").withIcon(GoogleMaterial.Icon.gmd_person).withSelectable(true),
                        new PrimaryDrawerItem().withName("Daftar Office").withIcon(GoogleMaterial.Icon.gmd_work).withSelectable(true),
                        new PrimaryDrawerItem().withName("Galery Video").withIcon(GoogleMaterial.Icon.gmd_video_library).withSelectable(true),
                        new PrimaryDrawerItem().withName("Galery Foto").withIcon(GoogleMaterial.Icon.gmd_image).withSelectable(true),
                        new PrimaryDrawerItem().withName("Keluar").withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withSelectable(true)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                // home fragment
                                fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                                active = homeFragment;
                                break;
                            case 2:
                                //about
                                fragmentManager.beginTransaction().hide(active).show(aboutFragment).commit();
                                active = aboutFragment;
                                break;
                            case 3:
                                //daftar karyawan
                                fragmentManager.beginTransaction().hide(active).show(karyawanFragment).commit();
                                active = karyawanFragment;
                                break;
                            case 4:
                                //daftar office
                                fragmentManager.beginTransaction().hide(active).show(officeFragment).commit();
                                active = officeFragment;
                                break;
                            case 5:
                                //daftar galery video
                                fragmentManager.beginTransaction().hide(active).show(GaleryVideo).commit();
                                active = GaleryVideo;
                                break;
                            case 6:
                                //daftar galery video
                                fragmentManager.beginTransaction().hide(active).show(GaleriFoto).commit();
                                active = GaleriFoto;
                                break;
                            case 7:
                                //keluar
                                break;
                        }
                        return false;
                    }
                }).build();
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
