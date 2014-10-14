package com.project.garvit.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class NavigationFragment extends Fragment {

    ListView drawerList;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationFragmentInterface ActivityInterface;
    interface NavigationFragmentInterface {
        public boolean NavigationOnOptionsItemSelected(MenuItem item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ActivityInterface = (NavigationFragmentInterface) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void init() {
        drawerList = (ListView) getActivity().findViewById(R.id.drawerList);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout,
                R.drawable.ic_drawer, R.string.drawerOpen, R.string.drawerClosed){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //To do something when drawer is closed
                Toast.makeText(getActivity().getApplicationContext(), "Drawer Closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //To do something when drawer is opened
                Toast.makeText(getActivity().getApplicationContext(), "Drawer Opened", Toast.LENGTH_SHORT).show();
            }
        };

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        drawerList.setAdapter(
                new myAdapter(getActivity())
        );

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        drawerLayout.setDrawerListener(drawerToggle);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Resources r = getResources();
                Toast.makeText(view.getContext(), "Option " + r.getStringArray(R.array.options)[i] + " is clicked", Toast.LENGTH_SHORT).show();
                drawerList.setItemChecked(i, true);
                selectItem(r.getStringArray(R.array.options)[i]);
            }
        });
    }


    public void selectItem(String title) {
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return ActivityInterface.NavigationOnOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    class myAdapter extends BaseAdapter {

        String[] options;
        int[] image = {
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher,
                R.drawable.ic_launcher};
        private Context context;

        myAdapter(Context context) {
            options = context.getResources().getStringArray(R.array.options);
            this.context = context;
        }

        @Override
        public int getCount() {
            return options.length;
        }

        @Override
        public Object getItem(int i) {
            return options[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = view;
            if(row == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row, viewGroup, false);
            }
            ImageView imageView = (ImageView) row.findViewById(R.id.image);
            TextView textView = (TextView) row.findViewById(R.id.option);
            textView.setText(options[i]);
            imageView.setImageResource(image[i]);
            return row;
        }
    }
}
