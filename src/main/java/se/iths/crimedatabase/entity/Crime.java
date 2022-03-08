package se.iths.crimedatabase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public Crime setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Crime setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Crime setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}
