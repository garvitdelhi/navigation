package com.project.garvit.navigation;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//Main Activity
public class MyActivity extends ActionBarActivity {

    ListView drawerList, listView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        drawerList = (ListView) findViewById(R.id.drawerList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Resources r = getResources();
                // Do something when an option is clicked in main listview
                Toast.makeText(view.getContext(), "Option " + r.getStringArray(R.array.options)[i] + " is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(new myListAdapter(this));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_drawer, R.string.drawerOpen, R.string.drawerClosed){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //To do something when drawer is closed
                Toast.makeText(getApplicationContext(), "Drawer Closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //To do something when drawer is opened
                Toast.makeText(getApplicationContext(), "Drawer Opened", Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerList.setAdapter(
                new myAdapter(this)
        );

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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItem(String title) {
        getSupportActionBar().setTitle(title);
    }
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

class myListAdapter extends BaseAdapter {

    String[] options, optionsHeading;
    int[] image = {
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher};
    private Context context;

    myListAdapter(Context context) {
        options = context.getResources().getStringArray(R.array.options);
        optionsHeading = context.getResources().getStringArray(R.array.optionsHeading);
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
            row = inflater.inflate(R.layout.list_single_row, viewGroup, false);
        }
        ImageView imageView = (ImageView) row.findViewById(R.id.image);
        TextView description = (TextView) row.findViewById(R.id.description);
        TextView title = (TextView) row.findViewById(R.id.title);
        description.setText(this.options[i]);
        title.setText(this.optionsHeading[i]);
        imageView.setImageResource(image[i]);
        return row;
    }
}
