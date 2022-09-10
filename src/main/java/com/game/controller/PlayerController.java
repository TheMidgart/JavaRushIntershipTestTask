package com.game.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.game.dto.PlayerDTO;
import com.game.dto.PlayerRequestParams;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.InvalidIdException;
import com.game.exceptions.InvalidPlayerException;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> all(@RequestParam(name = "name", required = false) String name,
                                            @RequestParam(name = "title", required = false) String title,
                                            @RequestParam(name = "race", required = false) Race race,
                                            @RequestParam(name = "profession", required = false) Profession profession,
                                            @RequestParam(name = "after", required = false) Long after,
                                            @RequestParam(name = "before", required = false) Long before,
                                            @RequestParam(name = "banned", required = false) Boolean banned,
                                            @RequestParam(name = "minExperience", required = false) Integer minExperience,
                                            @RequestParam(name = "maxExperience", required = false) Integer maxExperience,
                                            @RequestParam(name = "minLevel", required = false) Integer minLevel,
                                            @RequestParam(name = "maxLevel", required = false) Integer maxLevel,
                                            @RequestParam(name = "order", required = false) PlayerOrder order,
                                            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                            @RequestParam(name = "pageSize", required = false) Integer pageSize) {

        PlayerRequestParams params = new PlayerRequestParams(name, title, race, profession,
                after, before, banned, minExperience, maxExperience, minLevel, maxLevel,
                order, pageNumber, pageSize);

        return ResponseEntity.ok(service.getAllPlayers(params));

    }


   /* @PostMapping("/players")
    public ResponseEntity<Player> playerAdd(@RequestBody PlayerDTO player){
        player.isBanned();
        return ResponseEntity.ok(new Player());
    }*/

    @PostMapping("/players")
    public ResponseEntity<Player> playerAdd(@RequestBody PlayerDTO playerDTO) {


        Player playerForAdd = null;
        try {
            playerForAdd = service.createPlayer(playerDTO);
        } catch (InvalidPlayerException e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(playerForAdd);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity deletePlayer(@PathVariable Long id) {
        try {
            service.deletePlayer(id);
            return ResponseEntity.ok("");
        } catch (InvalidPlayerException e) {
            return ResponseEntity.status(404).body("Player not found");
        } catch (InvalidIdException e) {
            return ResponseEntity.status(400).body("Id is not valid");
        }
    }

    @PostMapping("/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        try {
            //Player player = service.updatePlayer(id, player);
            return ResponseEntity.ok(player);
        } catch (InvalidPlayerException e) {
            return ResponseEntity.status(404).body(null);
        } catch (InvalidIdException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}

