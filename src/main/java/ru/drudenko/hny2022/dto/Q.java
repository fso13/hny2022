package ru.drudenko.hny2022.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Q {
    private int id;
    private List<String> variables = new LinkedList<>();
    private String html;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Q q = (Q) o;
        return id == q.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
