package com.neeromeero.voiceassistant;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AI {
    public static String getAnswer(String user_question){


        Map <String, String> database = new HashMap<>(); {
            {
                database.put("привет", "И вам здрасте");
                database.put("как дела","Да вроде ничего");
                database.put("чем занимаешься","Отвечаю на дурацкие вопросы");
                database.put("как тебя зовут", "Я - голосовой помощник \uD83E\uDD55 v0.1 ");
                database.put("кто тебя создал","Господь\uD83D\uDE07, конечно же \uD83D\uDE44");
            }}

        user_question = user_question.toLowerCase();

        ArrayList<String> answers = new ArrayList<>();


        for (String database_question : database.keySet()){
            if(user_question.contains(database_question)){
                answers.add(database.get(database_question));
            }
        }

        if(answers.isEmpty()){
            return "Понял принял, не могу ответить";
        }
        return String.join(", ", answers);//разобраться. работает не на всех api

    }
}
