package com.neeromeero.voiceassistant;

import android.support.v4.util.Consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {
    public static void getAnswer(String user_question, final Consumer<String> callback ){


        Map <String, String> database = new HashMap<String, String>() {
            {
                put("привет", "И вам здрасте");
                put("как дела","Да вроде ничего");
                put("чем занимаешься","Отвечаю на дурацкие вопросы");
                put("как тебя зовут", "Я - голосовой помощник \uD83E\uDD55 v0.1 ");
                put("кто тебя создал", "NeeroMeero");
                put("кто сейчас президент России", "Посмотри по телевизору");
                put("какого цвета небо", "Вроде с утра было синее");
                put("есть ли жизнь на Марсе","Есть, но она об этом не знает");
            }};

        user_question = user_question.toLowerCase();

        final ArrayList<String> answers = new ArrayList<>();


        for (String database_question : database.keySet()){
            if(user_question.contains(database_question)){
                answers.add(database.get(database_question));
            }
        }

        Pattern cityPattern = Pattern.compile("какая погода в городе (\\p{L}+)",Pattern.CASE_INSENSITIVE);
        Matcher matcher = cityPattern.matcher(user_question);
        if(matcher.find()) {
            String cityName = matcher.group(1);
            Weather.get(cityName, new Consumer<String>() {
                @Override
                public void accept(String s) {
                    answers.add(s);
                    callback.accept(String.join(", ", answers));
                }
            });
        } else {
            if(answers.isEmpty()){
                callback.accept("Понял принял, не могу ответить");
                return;
            }
            callback.accept(String.join(", ", answers));//разобраться. работает не на всех api
        }
    }
}
