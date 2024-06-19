package com.bank.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bank.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

}
