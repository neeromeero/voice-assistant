package com.neeromeero.voiceassistant;

import java.util.Date;

public class Message {
    public String text;
    public Date date;
    public Boolean is_sent;

    public Message(String text, Boolean is_sent) {
        this.text = text;
        this.date = new Date();
        this.is_sent = is_sent;
    }
}
