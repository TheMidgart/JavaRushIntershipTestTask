package com.game.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.AbstractPlayer;
import com.game.entity.Profession;
import com.game.entity.Race;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;
@JsonAutoDetect
public class PlayerDTO implements AbstractPlayer, Serializable {
    Integer id;
    private String name;
    private String title;
    @Enumerated(EnumType.STRING)
    private Race race;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "yyyy-MM-dd")
    private Date birthday;
    private Boolean banned;
    private Integer experience;

    private Integer level;
    private Integer untilNextLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public PlayerDTO() {
    }

    public PlayerDTO(String name, String title, Race race, Profession profession, Date birthday, boolean isBanned, Integer experience) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = isBanned;
        this.experience = experience;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public Profession getProfession() {
        return profession;
    }

    @Override
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public Boolean isBanned() {
        return banned;
    }

    @Override
    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public Integer getExperience() {
        return experience;
    }

    @Override
    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
