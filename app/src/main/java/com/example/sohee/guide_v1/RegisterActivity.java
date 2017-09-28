package com.example.sohee.guide_v1;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;




public class RegisterActivity extends AppCompatActivity{

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> nameAdapter;
    ArrayList<String> numList;
    ArrayList<String> nameList;


    EditText numname;
    EditText numname2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        numname = (EditText)findViewById(R.id.nameedittext);
        numname2 =(EditText)findViewById(R.id.numedittext);

        numList = new ArrayList<String>();
        nameList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,R.layout.simpleitem,numList);

        Button regbutton = (Button)findViewById(R.id.registbtn);

        final ListView numlist = (ListView)findViewById(R.id.registlistView);
        final ListView namelist = (ListView)findViewById(R.id.registlistView);


        numlist.setAdapter(adapter);
        namelist.setAdapter(adapter);


        numlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                adapter.remove(selectedItem);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),"삭제되었습니다!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        regbutton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)    {

                numList.add(0,numname2.getText().toString() +" " +numname.getText().toString());
                adapter.notifyDataSetChanged();
                numname.setText("");
                numname2.setText("");

            }
        });




    }

}
