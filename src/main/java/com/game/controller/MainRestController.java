package com.game.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRestController {

    @Autowired
    private PlayerRepository repository;

    @GetMapping("/rest/players")
     public ResponseEntity<List<Player>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/rest/players")
    public ResponseEntity<Player> playerAdd(JSONO){
        if (object==null){
        }
        object = null;
        return null;
    }
    @DeleteMapping("/rest/players/{id}")
    void deletePlayer(@PathVariable Long id) {
        repository.deleteById(id);
    }
    }

