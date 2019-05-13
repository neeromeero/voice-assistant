package com.neeromeero.voiceassistant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MessageView extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView messageTime;


    public MessageView(View itemView){
        super(itemView);
        messageTime = itemView.findViewById(R.id.messageTime);
        messageText = itemView.findViewById(R.id.messageText);
    }

    public void bind(Message message){
        messageText.setText(message.text);
        DateFormat fmt = new SimpleDateFormat("HH:mm");
        messageTime.setText(fmt.format(message.date));
    }
}
