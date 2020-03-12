package com.watconsult.tlakapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.watconsult.tlakapp.services.MySingleton;
import com.watconsult.tlakapp.ui.chat.ChatBubble;
import com.watconsult.tlakapp.ui.chat.MessageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupChatActivity extends AppCompatActivity {

    EditText edtTitle;
    EditText edtMessage;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAj5hlR6E:APA91bHSov6WjOoeHpmt5nHOCpDaPiiLbWYmjZ9pgM1D6LJvZPetZH6LloeY15G6_vpEEXgy68qEkkmj2pEsmfNQqqrABGRoXfo7BBpJtyYLIx9lrtdRaX3QvW5jT0gVBqrUxOCqw1Ou";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;



    ImageView back_btn;

    private ListView listView;
    private ImageButton btnSend;
    private EditText editText;
    boolean myMessage = true;
    private List<ChatBubble> ChatBubbles;
    private ArrayAdapter<ChatBubble> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen_layout);
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

        //set ListView adapter first
        adapter = new MessageAdapter(this, R.layout.left_chat_bubble, ChatBubbles);
        listView.setAdapter(adapter);

        //event for button SEND
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String msg =  editText.getText().toString();
                TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
             //   NOTIFICATION_TITLE = edtTitle.getText().toString();
                NOTIFICATION_MESSAGE = msg;

                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", "TLakApp");
                    notifcationBody.put("message", NOTIFICATION_MESSAGE);

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage() );
                }
                sendNotification(notification);

                if (msg.equals("")) {
                    Toast.makeText(GroupChatActivity.this, "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    ChatBubble ChatBubble = new ChatBubble(msg, myMessage);
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

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        editText.setText("");
                     //   edtTitle.setText("");
                        //edtMessage.setText("");
                    }
                },
                error -> {
                    Toast.makeText(GroupChatActivity.this, "Request error", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "onErrorResponse: Didn't work");
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


}
