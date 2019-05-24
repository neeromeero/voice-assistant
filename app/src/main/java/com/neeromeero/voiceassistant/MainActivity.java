package com.neeromeero.voiceassistant;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    protected Button sendButton;
    protected EditText userMessage;
    protected RecyclerView chatWindow;
    protected TextToSpeech tts;

    protected Button left_button;
    protected Button right_button;
    protected MessageController messageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        left_button = findViewById(R.id.left_button);
        right_button = findViewById(R.id.right_button);

        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("com.neeromeero.voiceassistant.Left_activity");
                startActivity(intent1);
            }
        });

        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent("com.neeromeero.voiceassistant.Right_activity");
                startActivity(intent2);
            }
        });
        //

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

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(new Locale("ru"));
            }
        });
    }

    protected void onClickListener() {
        String message = userMessage.getText().toString();// текст пользователйа
        userMessage.setText("");
        messageController.messageList.add(new Message(message,true));
        AI.getAnswer(message, new Consumer<String>() {
            @Override
            public void accept(String answer) {
                messageController.messageList.add(new Message(answer,false));
                tts.speak(answer,TextToSpeech.QUEUE_FLUSH,null,null);
                messageController.notifyDataSetChanged();
                chatWindow.scrollToPosition(messageController.messageList.size() - 1);

            }
        });
    }
}
