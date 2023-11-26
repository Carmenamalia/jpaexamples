package com.springapps.jpaexamples.twitterapp;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tweet {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String text;

    @ManyToOne //relatia dintre tabelele user si tweet---mai multe tweet la un user
    @JoinColumn(name="user_id")//cheia straina din tabelul tweet
    private User user;

    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL,orphanRemoval = true)
    //un tweet are mai multe comment
    //fiecare comment din lista se leaga de un tweet
    private List<Comment> comments;

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                //", comments=" + comments +
                '}';
    }
}
