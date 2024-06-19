package com.bank.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.exception.InvalidIdException;
import com.bank.bank.model.Player;
import com.bank.bank.repository.PlayerRepository;

@Service
public class PlayerService {
@Autowired
private PlayerRepository playerRepository;
	public Player addPlayer(Player player) {
		// TODO Auto-generated method stub
		return playerRepository.save(player);
	}
	public List<Player> allPlayers() {
		// TODO Auto-generated method stub
		return playerRepository.findAll();
	}
	public Player onePlayer(int pid) throws InvalidIdException{
		// TODO Auto-generated method stub
		Optional<Player> optional=playerRepository.findById(pid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Player Id is invald");
		}
		return optional.get();
	}
	public void delete(int pid) {
		// TODO Auto-generated method stub
		playerRepository.deleteById(pid);
	}

}
