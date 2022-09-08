package com.game.service;

import com.game.dto.PlayerDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidIdException;
import com.game.exceptions.InvalidPlayerException;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PlayerService {
    public static final Long TWO_THOUSAND_DATE_IN_MIL=946674000000L;
    public static final Long THREE_THOUSAND_DATE_IN_MIL=32503669200000L;

    @Autowired
    private PlayerRepository repository;

    public Player createPlayer(PlayerDTO playerDTO) throws InvalidPlayerException {
        if (!checkPlayer(playerDTO)) {
            throw new InvalidPlayerException();
        } else {
            Integer experience = playerDTO.getExperience();
            Player player = new Player(playerDTO.getName(), playerDTO.getTitle(),
                    playerDTO.getRace(), playerDTO.getProfession(),
                    playerDTO.getBirthday(), playerDTO.isBanned(),
                    experience, calculateLevel(experience), calculateUntilNextLevel(experience));
            repository.save(player);
            return player;
        }
    }

    public void deletePlayer(Long id) throws InvalidIdException, InvalidPlayerException {
        if (!(id instanceof Long)||id==null){
            throw new InvalidIdException();
        }
        if (!repository.existsById(id)){
            throw new InvalidPlayerException();
        }
        repository.deleteById(id);

    }
    public Player updatePlayer(Long id, PlayerDTO playerDTO) throws InvalidIdException, InvalidPlayerException {
        if (!(id instanceof Long)||id==null){
            throw new InvalidIdException();
        }
        if (!repository.existsById(id)){
            throw new InvalidPlayerException();
        }
        Player playerForUpdate =  repository.findById(id).get();
        if (playerDTO.getName()!=null) playerForUpdate.setName(playerDTO.getName());
        if (playerDTO.getTitle()!=null) playerForUpdate.setTitle(playerDTO.getTitle());
        if (playerDTO.getBirthday()!=null) playerForUpdate.setBirthday(playerDTO.getBirthday());
        if (playerDTO.getRace()!=null) playerForUpdate.setRace(playerDTO.getRace());
        if (playerDTO.getProfession()!=null) playerForUpdate.setProfession(playerDTO.getProfession());
        if (playerDTO.getExperience()!=null){
            Integer exp = playerDTO.getExperience();
            playerForUpdate.setExperience(exp);
            playerForUpdate.setLevel(exp);
            playerForUpdate.setUntilNextLevel(exp);
        }
        playerForUpdate.setBanned(playerDTO.isBanned());
        repository.save(playerForUpdate);
        return playerForUpdate;

    }

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    private boolean checkPlayer(PlayerDTO playerDTO) {
        if (playerDTO.getName().length() > 12 || playerDTO.getName() == null) return false;
        if (playerDTO.getTitle().length() > 30 || playerDTO.getTitle() == null) return false;
        if (playerDTO.getBirthday() == null) return false;
        if (playerDTO.getBirthday().before(new Date(TWO_THOUSAND_DATE_IN_MIL))) return false;
        if (playerDTO.getBirthday().after(new Date(THREE_THOUSAND_DATE_IN_MIL))) return false;
        if (playerDTO.getProfession() == null) return false;  //мб проверка на вхождение в enum Prof
        if (playerDTO.getRace() == null) return false;        //мб проверка на вхождение в enum
        if (playerDTO.getExperience() < 0 || playerDTO.getExperience() > 10000000) return false;
        return true;
    }

    private Integer calculateUntilNextLevel(Integer experience) {
        Integer level = calculateLevel(experience);
        return 50*(level+1)*(level+2)-experience;
    }

    private Integer calculateLevel(Integer experience) {

        Integer result = (int) Math.sqrt(2500+experience*200);
        result = (result-50)/100;
        return  result;
    }

}
