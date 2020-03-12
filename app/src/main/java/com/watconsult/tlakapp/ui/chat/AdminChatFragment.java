package com.watconsult.tlakapp.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ui.mygroup.MyGroupViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdminChatFragment extends Fragment {
    private ListView listView;
    private ImageButton btnSend;
    private EditText editText;
    boolean myMessage = true;
    private List<ChatBubble> ChatBubbles;
    private ArrayAdapter<ChatBubble> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            View root = inflater.inflate(R.layout.admin_chat_screen, container, false);
      /*  ChatBubbles = new ArrayList<>();

        listView = (ListView) root.findViewById(R.id.list_msg);
        btnSend = (ImageButton) root.findViewById(R.id.btn_chat_send);
        editText = (EditText) root.findViewById(R.id.msg_type);
        adapter = new MessageAdapter(getActivity(), R.layout.left_chat_bubble, ChatBubbles);
        listView.setAdapter(adapter);*/
/*        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "Please input some text...", Toast.LENGTH_SHORT).show();
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
        });*/

        return root;
    }
}
