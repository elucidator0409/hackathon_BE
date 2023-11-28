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
    private int no;

    @Column(nullable = false, unique = false)
    private String email;

    @Column(nullable = false, unique = false)
    private Integer score; // Use Integer to handle null values

    public int getNo() {
        return no;
    }

    public String getEmail() {
        return email;
    }

    public Integer getScore() {
        return score;
    }


    public void setNo(int no) {
        this.no = no;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(Integer score) {
        this.score = score;
    }



    @Override
    public String toString() {
        return "MockData{" +
                "id=" + id +
                ", no=" + no +
                ", email='" + email + '\'' +
                ", score=" + score +

                '}';
    }
}
