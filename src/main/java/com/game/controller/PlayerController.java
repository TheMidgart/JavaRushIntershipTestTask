package com.game.controller;

import com.game.dto.PlayerDTO;
import com.game.dto.PlayerRequestParams;
import com.game.entity.Player;
import com.game.exceptions.InvalidIdException;
import com.game.exceptions.InvalidPlayerException;
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
    public ResponseEntity<List<Player>> all(@ModelAttribute PlayerRequestParams params) {
        return ResponseEntity.ok(service.getAllPlayers(params));
    }

    @GetMapping("players/count")
    public ResponseEntity<Integer> getCount(@ModelAttribute PlayerRequestParams params){
        return ResponseEntity.ok(service.getPlayersCount(params));
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.getPlayerById(id));
        } catch (InvalidPlayerException e) {
            return ResponseEntity.status(404).body(null);
        } catch (InvalidIdException e) {
            return ResponseEntity.status(400).body(null);
        }

    }




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
            Player upPlayer = service.updatePlayer(id, player);
            return ResponseEntity.ok(player);
        } catch (InvalidPlayerException e) {
            return ResponseEntity.status(404).body(null);
        } catch (InvalidIdException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}

