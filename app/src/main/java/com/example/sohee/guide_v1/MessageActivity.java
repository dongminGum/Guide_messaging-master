package com.example.sohee.guide_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public  class MessageActivity extends AppCompatActivity{
    private  static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_msg;
    FloatingActionButton fab;

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() ==R.id.menu_sign_out){
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_msg,"You have been signed out.",Snackbar.LENGTH_SHORT).show();
                    finish();

                }
            });

        }
        return  true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==SIGN_IN_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Snackbar.make(activity_msg,"Succesfully signed in. Welcome!",Snackbar.LENGTH_SHORT).show();
            }
            else{
                Snackbar.make(activity_msg,"We couldn't sign you in. Please try again later",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        activity_msg = (RelativeLayout)findViewById(R.id.activity_msg);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                EditText input = (EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });

        //Check if not sign in then navigate Sign in page
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        else{
            Snackbar snackbar = Snackbar.make(activity_msg,FirebaseAuth.getInstance().getCurrentUser().getEmail()+"님, 채팅에 입장하셨습니다. ",Snackbar.LENGTH_SHORT);
            View view = snackbar.getView();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);
            snackbar.show();
            //Load Content
            displayChatMessage();
        }

    }

    private void displayChatMessage() {
        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
        listOfMessage.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        adapter = new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {
            protected void populateView(View v, ChatMessage model, int position){
                //Get references to the views of list_item.xml

                TextView messageText, messageUser, messageTime;
                messageText = (TextView)v.findViewById(R.id.message_text);
                messageUser = (TextView)v.findViewById(R.id.message_user);
                messageTime = (TextView)v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("yyyy/MM/dd (HH:mm)",model.getMessageTime()));

            }
        };

        listOfMessage.setAdapter(adapter);
    }

}
