package com.project.garvit.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Main_Screen extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main__screen, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Resources r = getResources();
                // Do something when an option is clicked in main listview
                Toast.makeText(view.getContext(), "Option " + r.getStringArray(R.array.options)[i] + " is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(new myListAdapter(getActivity()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

}
