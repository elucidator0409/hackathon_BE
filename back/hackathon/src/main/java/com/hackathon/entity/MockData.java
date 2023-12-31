package com.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class MockData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String type;

    @Column(nullable = false, unique = false)
    private int no;

    @Column(nullable = false, unique = false)
    private String email;

    @Column(nullable = false, unique = false)
    private Double score;

    public int getNo() {
        return no;
    }

    public String getEmail() {
        return email;
    }

    public Double getScore() {
        return score;
    }


    public void setNo(int no) {
        this.no = no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(Double score) {
        this.score = score;
    }




}
