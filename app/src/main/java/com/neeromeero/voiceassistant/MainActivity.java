package com.neeromeero.voiceassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected Button sendButton;
    protected EditText userMessage;
    protected RecyclerView chatWindow;

    protected MessageController messageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        userMessage = findViewById(R.id.userMessage);
        chatWindow = findViewById(R.id.chatWindow);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener();

            }
        });

        messageController = new MessageController();

        chatWindow.setLayoutManager(new LinearLayoutManager(this));
        chatWindow.setAdapter(messageController);
    }

    protected void onClickListener() {
        String message = userMessage.getText().toString();// текст пользователйа
        userMessage.setText("");
        messageController.messageList.add(new Message(message,true));
        String answer = AI.getAnswer(message);
        messageController.messageList.add(new Message(answer,false));

        messageController.notifyDataSetChanged();
        chatWindow.scrollToPosition(messageController.messageList.size()-1);

    }
}
