package ru.drudenko.hny2022.service;

import org.springframework.stereotype.Service;
import ru.drudenko.hny2022.dto.A;
import ru.drudenko.hny2022.dto.Q;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class QService {

    private final SortedMap<Q, A> qa = new TreeMap<>(Comparator.comparingInt(Q::getId));

    @PostConstruct
    public void init() {

        qa.put(Q.builder()
                        .id(1)
                        .html("Поднявшись по старой каменной лестнице, вы попали в небольшую башню старца Фура. \n" +
                                "Мой друг…я рад что ты добрался сюда, теперь я могу загадать тебе мое первое задание! Вот моя загадка:\n" +
                                "Узкий и железный рот, быстро бумагу украдет,\n" +
                                "А потом и не достанешь, если ключик не покажешь. ")
                        .build(),
                A.builder().answer("старец").build());
    }

    public Q start() {
        return qa.firstKey();
    }

    public Q postAnswer(A answer) {
        A a = qa.get(Q.builder().id(answer.getQId()).build());
        if (a.getAnswer().equals(answer.getAnswer())) {
            return qa.keySet().stream().filter(q -> q.getId() == (answer.getQId() + 1)).findFirst().orElse(Q.builder().html("Ура, вы прошли.").build());
        } else {
            return qa.keySet().stream().filter(q -> q.getId() == (answer.getQId())).findFirst().orElse(null);
        }
    }

}
