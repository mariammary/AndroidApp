package com.example.maryam.androidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Listitem extends AppCompatActivity {
    ListView listView, listview2;
    Button btn;
    private Button select, upload, registerr;


    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        select = (Button) findViewById(R.id.choosebtn);
        upload = (Button) findViewById(R.id.uploadbtn);

        //listView= (ListView) findViewById(R.id.mylist);

        //ArrayAdapter<String>myadapter=new ArrayAdapter<String>(this,R.layout.item_list, R.id.textView,names);
        //listView.setAdapter(myadapter);

        listview2 = (ListView) findViewById(R.id.mylist2);
        for (int i = 0; i < 10; i++) {
            User user = new User("name" + i, "phone" + i, R.drawable.ic_launcher);
            users.add(user);
        }
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(inflater);
        listview2.setAdapter(myCustomAdapter);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = users.get(i);
                Toast.makeText(Listitem.this, "clicked on" + user.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class MyCustomAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public MyCustomAdapter(LayoutInflater inflater) {
            this.inflater = inflater;

        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {
            if (convertview == null) {
                convertview = inflater.inflate(R.layout.listitem, parent, false);
            }
            User user = users.get(position);
            TextView textViewName = (TextView) convertview.findViewById(R.id.name);
            TextView textViewPhone = (TextView) convertview.findViewById(R.id.phone);
            ImageView imageView = (ImageView) convertview.findViewById(R.id.img);
            textViewName.setText(user.getName());
            textViewPhone.setText(user.getPhone());
            imageView.setImageResource(user.getImg());
            return convertview;
        }
    }
}
