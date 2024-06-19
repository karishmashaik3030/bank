package com.bank.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.enums.Role;
import com.bank.bank.exception.InvalidIdException;
import com.bank.bank.model.Player;
import com.bank.bank.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
@Autowired
private PlayerService playerService;
@PostMapping("/add")
public Player addPlayer(@RequestBody Player player) {
	player.setRole(Role.BATSMAN);
	player=playerService.addPlayer(player);
	return player;
}
@GetMapping("/all")
public List<Player> allPlayers() {
	return playerService.allPlayers();
}

@GetMapping("/one/{pid}")
public ResponseEntity<?> onePlayer(@PathVariable("pid") int pid) {
	try {
		Player player=playerService.onePlayer(pid);
		return ResponseEntity.ok().body(player);
	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
@PutMapping("/update/{pid}")
public ResponseEntity<?> updatePlayer(@RequestBody Player newPlayer,@PathVariable("pid") int pid) {
	try {
		Player player=playerService.onePlayer(pid);
		if(newPlayer.getName()!=null)
			player.setName(newPlayer.getName());
		if(newPlayer.getNumber()!=0)
			player.setNumber(newPlayer.getNumber());
		if(newPlayer.getRole()!=null)
			player.setRole(newPlayer.getRole());
		if(newPlayer.getTotalMatches()!=0)
			player.setTotalMatches(newPlayer.getTotalMatches());
		if(newPlayer.getTeamName()!=null)
			player.setTeamName(newPlayer.getTeamName());
		if(newPlayer.getState()!=null)
			player.setState(newPlayer.getState());
		if(newPlayer.getDescription()!=null)
			player.setDescription(newPlayer.getDescription());
		player=playerService.addPlayer(player);
		return ResponseEntity.ok().body(player);
	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		return ResponseEntity.badRequest().body("Player Id is invalid");
	}
}
@DeleteMapping("/delete/{pid}")
public ResponseEntity<?> delete(@PathVariable("pid") int pid) {
	try {
		Player player=playerService.onePlayer(pid);
		playerService.delete(pid);
		return ResponseEntity.ok().body("Player deleted successfully");
	} catch (InvalidIdException e) {
		// TODO Auto-generated catch block
		return ResponseEntity.badRequest().body("Player Id Invalid");
	}
}
}
