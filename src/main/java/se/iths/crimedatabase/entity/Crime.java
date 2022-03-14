package se.iths.crimedatabase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

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

    public Category getCategory() {
        return category;
    }

    public Crime setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Crime setAddress(Address address) {
        this.address = address;
        return this;
    }

}
