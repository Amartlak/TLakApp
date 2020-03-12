package com.watconsult.tlakapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.ui.chat.ChatBubble;
import com.watconsult.tlakapp.ui.chat.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminChatActivity  extends AppCompatActivity {
    ImageView back_btn;
    private ListView listView;
    private ImageButton btnSend;
    ImageButton fileUpload;
    private EditText editText;
    Uri uri;
    boolean myMessage = true;
    private List<ChatBubble> ChatBubbles;
    public static final int REQUEST_IMAGE = 100;
    static final int RESULT_LOAD_IMAGE = 1, CAMERA = 2, BROWSEIMG = 3;
    private static final String TAG = AdminChatActivity.class.getSimpleName();
    private ArrayAdapter<ChatBubble> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_chat_screen);
        back_btn = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ChatBubbles = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_msg);
        btnSend = (ImageButton) findViewById(R.id.btn_chat_send);
        editText = (EditText) findViewById(R.id.msg_type);
        fileUpload = (ImageButton) findViewById(R.id.upload);

        //set ListView adapter first
        adapter = new MessageAdapter(this, R.layout.left_chat_bubble, ChatBubbles);
        listView.setAdapter(adapter);

        //event for button SEND
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(AdminChatActivity.this, "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    ChatBubble ChatBubble = new ChatBubble(editText.getText().toString(), myMessage);
                    ChatBubbles.add(ChatBubble);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    if (myMessage) {
                        myMessage = false;
                    } else {
                        myMessage = true;
                    }
                }
            }
        });
    }
    }

