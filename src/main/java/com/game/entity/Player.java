package com.game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.AbstractPlayer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "player")
public class Player implements Serializable, AbstractPlayer {
    //Creating fields which located in DAO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 12, nullable = false)
    private String name;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Race race;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name = "banned",columnDefinition = "false")
    private Boolean banned;

    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;


    /*Constructors*/

    public Player() {
    }

    public Player(String name, String title, Race race, Profession profession, Date birthday, boolean isBanned,
                    Integer experience, Integer level, Integer untilNextLevel) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = isBanned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;

    }

    /* getters and setters for fields    */


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean isBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    /*overring*/

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", birthday=" + birthday +
                ", isBanned=" + banned +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return banned == player.banned && id.equals(player.id) && name.equals(player.name)
                && title.equals(player.title) && race == player.race && profession == player.profession
                && birthday.equals(player.birthday) && experience.equals(player.experience)
                && level.equals(player.level) && untilNextLevel.equals(player.untilNextLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, race, profession, birthday, banned, experience, level, untilNextLevel);
    }
}