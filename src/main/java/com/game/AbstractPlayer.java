package com.game;

import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Date;

public interface AbstractPlayer {
    public String getName();
    public void setName(String name);
    public String getTitle();
    public void setTitle(String title);
    public Race getRace();
    public void setRace(Race race);
    public Profession getProfession();
    public void setProfession(Profession profession);
    public Date getBirthday();
    public void setBirthday(Date birthday);
    public boolean isBanned();
    public void setBanned(boolean banned);
    public Integer getExperience();
    public void setExperience(Integer experience);
}
