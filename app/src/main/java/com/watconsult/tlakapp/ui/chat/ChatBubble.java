package com.watconsult.tlakapp.ui.chat;

        import android.widget.ImageButton;

public class ChatBubble {

    private String content;
    private boolean myMessage;

    public ChatBubble(String content,boolean myMessage) {
        this.content = content;
        this.myMessage = myMessage;
    }

    public ChatBubble(String content, boolean myMessage, String toString) {
    }


    public String getContent() {
        return content;
    }

    public boolean myMessage() {
        return myMessage;
    }


}
