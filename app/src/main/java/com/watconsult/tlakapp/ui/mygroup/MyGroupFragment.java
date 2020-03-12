package com.watconsult.tlakapp.ui.mygroup;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.watconsult.tlakapp.AdminChatActivity;
import com.watconsult.tlakapp.GroupChatActivity;
import com.watconsult.tlakapp.R;
public class MyGroupFragment extends Fragment {

private MyGroupViewModel shareViewModel;
LinearLayout tourAdmin,groupChat;
TextView conversion, message,name,gname,gmessage;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(MyGroupViewModel.class);
        View root = inflater.inflate(R.layout.chat_detail_layout, container, false);
        conversion = (TextView) root.findViewById(R.id.conversation);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        conversion.setTypeface(custom_font1);
        name = (TextView) root.findViewById(R.id.name);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        conversion.setTypeface(custom_font2);
        message = (TextView) root.findViewById(R.id.message);
        Typeface custom_font3 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        conversion.setTypeface(custom_font3);
        gname = (TextView) root.findViewById(R.id.g_name);
        Typeface custom_font4 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        gname.setTypeface(custom_font4);
        gmessage = (TextView) root.findViewById(R.id.g_message);
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        gmessage.setTypeface(custom_font5);
       tourAdmin = root.findViewById(R.id.adminclick);
        tourAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminChatActivity.class);
                startActivity(intent);
              /*  AdminChatFragment chatFragment = new AdminChatFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.chatpage_replace, chatFragment).commit();*/
            }
        });
         groupChat = root.findViewById(R.id.gchatclick);
        groupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* GroupChatFragment groupChatFragment = new GroupChatFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.chatpage_replace, groupChatFragment).commit();*/
                Intent intent = new Intent(getActivity(), GroupChatActivity.class);
                startActivity(intent);
            }
        });
       /* final TextView textView = root.findViewById(R.id.text_share);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}