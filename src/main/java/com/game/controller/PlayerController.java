package com.game.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.game.dto.PlayerDTO;
import com.game.entity.Player;
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
    public ResponseEntity<List<Player>> all() {
        return ResponseEntity.ok(service.getAllPlayers());
    }

   /* @PostMapping("/players")
    public ResponseEntity<Player> playerAdd(@RequestBody PlayerDTO player){
        player.isBanned();
        return ResponseEntity.ok(new Player());
    }*/

   @PostMapping("/players")
     public ResponseEntity<Player> playerAdd(@RequestBody PlayerDTO playerDTO){
        Player playerForAdd = null;
        try {
             playerForAdd = service.createPlayer(playerDTO);
        }
        catch (InvalidPlayerException e){
            return ResponseEntity.badRequest().body(null);
        }
         return ResponseEntity.ok(playerForAdd);
    }
    @DeleteMapping("/players/{id}")
    public ResponseEntity deletePlayer(@PathVariable Long id) {
        try {
            service.deletePlayer(id);
            return ResponseEntity.ok("");
        }
        catch (InvalidPlayerException e){
            return ResponseEntity.status(404).body("Player not found");
        }
        catch (InvalidIdException e){
            return ResponseEntity.status(400).body("Id is not valid");
        }
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO){
        try {
            Player player = service.updatePlayer(id, playerDTO);
            return ResponseEntity.ok(player);
        }
        catch (InvalidPlayerException e){
            return ResponseEntity.status(404).body(null);
        }
        catch (InvalidIdException e){
            return ResponseEntity.status(400).body(null);
        }
    }
}

