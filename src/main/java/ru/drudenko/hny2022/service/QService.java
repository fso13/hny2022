package ru.drudenko.hny2022.service;

import org.springframework.stereotype.Service;
import ru.drudenko.hny2022.dto.A;
import ru.drudenko.hny2022.dto.Q;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class QService {

    private final SortedMap<Q, A> qa = new TreeMap<>(Comparator.comparingInt(Q::getId));

    @PostConstruct
    public void init() {

        qa.put(Q.builder()
                        .id(1)
                        .html("Кто в 1700 году издал указ, согласно которому Новый год необходимо отмечать первого января?")
                        .build(),
                A.builder().answer("петр первый").build());

        qa.put(Q.builder()
                        .id(2)
                        .html("Какую телепередачу показывали советским людям каждый Новый год?")
                        .build(),
                A.builder().answer("голубой огонек").build());
        qa.put(Q.builder()
                        .id(3)
                        .html("Какая башня Московского Кремля появляется на экранах в новогоднюю ночь?")
                        .variables(List.of("НИКОЛЬСКАЯ",
                                "СПАССКАЯ",
                                "КУТАФЬЯ",
                                "БОРОВИЦКАЯ"))
                        .build(),
                A.builder().answer("СПАССКАЯ").build());//никольская кутафья боровицкая
        qa.put(Q.builder()
                        .id(4)
                        .html("Каким образом китайцы отгоняют от дома злых духов во время празднования нового года?")
                        .build(),
                A.builder().answer("фейерверк").build());
        qa.put(Q.builder()
                        .id(5)
                        .html("Кто в 23.55 выступал перед россиянами по ТВ 31 декабря 1991 года?")
                        .variables(List.of("Михаил Горбачёв",
                                "Михаил Жванецкий",
                                "Михаил Задорнов",
                                "Михаил Евдокимов"))
                        .build(),
                A.builder().answer("Михаил Задорнов").build());
        qa.put(Q.builder()
                        .id(6)
                        .html("Как ласково называют Деда Мороза в некоторых русских сказках?")
                        .build(),
                A.builder().answer("Морозко").build());
        qa.put(Q.builder()
                        .id(7)
                        .html("В каком городе живет Дед Мороз?")
                        .build(),
                A.builder().answer("Великий Устюг").build());
        qa.put(Q.builder()
                        .id(8)
                        .html("Какой российский город празднует наступление Нового года позднее других?")
                        .build(),
                A.builder().answer("Калининград").build());
        qa.put(Q.builder()
                        .id(9)
                        .html("Сколько времени в Нью-Йорке, когда в Москве наступает Новый год?")
                        .variables(List.of("8 часов утра",
                                "4 часа дня",
                                "12 часов дня",
                                "7 часов вечера"))
                        .build(),
                A.builder().answer("4 часа дня").build()); //
        qa.put(Q.builder()
                        .id(10)
                        .html("В какой стране в полночь принято обязательно поцеловаться?")
                        .build(),
                A.builder().answer("США").build());

    }

    public Q start() {
        return qa.firstKey();
    }

    public Q postAnswer(A answer) {
        A a = qa.get(Q.builder().id(answer.getQId()).build());
        if (a.getAnswer().equalsIgnoreCase(answer.getAnswer())) {
            return qa.keySet().stream().filter(q -> q.getId() == (answer.getQId() + 1)).findFirst().orElse(Q.builder().html("Ура, вы прошли.").build());
        } else {
            return qa.keySet().stream().filter(q -> q.getId() == (answer.getQId())).findFirst().orElse(Q.builder().html("Ура, вы прошли.").build());
        }
    }

}
