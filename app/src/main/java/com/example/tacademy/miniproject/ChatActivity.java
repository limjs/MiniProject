package com.example.tacademy.miniproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.tacademy.miniproject.data.ChatContract;
import com.example.tacademy.miniproject.data.User;
import com.example.tacademy.miniproject.manager.DBManager;

public class ChatActivity extends AppCompatActivity {

    RecyclerView listView;
    ChatAdapter mAdapter;
    RadioGroup typeView;
    EditText inputView;
    public static final String EXTRA_USER = "user";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listView = (RecyclerView)findViewById(R.id.rv_list);
        typeView = (RadioGroup)findViewById(R.id.group_type);
        inputView = (EditText)findViewById(R.id.edit_input);

        mAdapter = new ChatAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        Button btn = (Button)findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String message = inputView.getText().toString();
                int type = ChatContract.ChatMessage.TYPE_SEND;
                switch (typeView.getCheckedRadioButtonId()) {
                    case R.id.radio_send :
                        type = ChatContract.ChatMessage.TYPE_SEND;
                        break;
                    case R.id.radio_receive :
                        type = ChatContract.ChatMessage.TYPE_RECEIVE;
                        break;
                }
                DBManager.getInstance().addMessage(user, type, message);

                updateMessage();
            }
        });
    }

    private void updateMessage() {
        Cursor c = DBManager.getInstance().getChatMessage(user);
        mAdapter.changeCursor(c);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMessage();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
    }
}
