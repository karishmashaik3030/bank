package com.bank.bank.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.dto.DetailsDto;
import com.bank.bank.dto.UpdateDto;
import com.bank.bank.enums.AccountType;
import com.bank.bank.exception.InvalidIdException;
import com.bank.bank.model.Account;
import com.bank.bank.model.AccountDetails;
import com.bank.bank.model.AccountHolder;
import com.bank.bank.model.Executive;
import com.bank.bank.service.AccountDetailsService;
import com.bank.bank.service.AccountHolderService;
import com.bank.bank.service.AccountService;
import com.bank.bank.service.ExecutiveService;


@RestController
@RequestMapping("/details")
public class AccountDetailsController {
	@Autowired
	private AccountDetailsService accountDetailsService;
	@Autowired
	private AccountHolderService accountHolderService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ExecutiveService executiveService;
	@PostMapping("/add")
public ResponseEntity<?> postDetails(@RequestBody DetailsDto detailsDto) {
		
				try {
					AccountHolder accountHolder=accountHolderService.getByAccountId(detailsDto.getAccountHolderId());
					Account account=accountService.getByAccountId(detailsDto.getAccountId());
					Executive executive=executiveService.getByExecutiveId(detailsDto.getExecutiveId());
					AccountDetails accountDetails=new AccountDetails();
					accountDetails.setDateOfCreation(LocalDate.now());
					accountDetails.setAccountHolder(accountHolder);
					accountDetails.setAccount(account);
					accountDetails.setExecutive(executive);
					accountDetails=accountDetailsService.postDetails(accountDetails);
					return ResponseEntity.ok().body(accountDetails);
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					return ResponseEntity.badRequest().body(e.getMessage());
				}
		
}
	@GetMapping("/getAllByEid/{eid}")
	public ResponseEntity<?> getAllHolders(@PathVariable("eid") int eid) {
		
		try {
			//validate vendorId
			Executive executive=executiveService.getByExecutiveId(eid);
			List<Integer> id=accountDetailsService.getHolderId(eid);
			System.out.println(id);
			List<AccountHolder> aclist=new ArrayList<>();
			for(Integer i:id) {
			AccountHolder li=accountHolderService.getByAccountId(i);
			//List<AccountHolder> aclist;
			aclist.add(li);
			}
			return ResponseEntity.ok().body(aclist);
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/getAll/{type}")
	public ResponseEntity<?> getAllByPath(@PathVariable("type") AccountType type) {
		
		try {
			//validate vendorId
			int aid=accountService.getByPath(type);
			System.out.println(aid);
			Account account=accountService.getByAccountId(aid);
			List<Integer> id=accountDetailsService.getAccountHolderId(aid);
			System.out.println(id);
			List<AccountHolder> aclist=new ArrayList<>();
			for(Integer i:id) {
			AccountHolder li=accountHolderService.getByAccountId(i);
			//List<AccountHolder> aclist;
			aclist.add(li);
			}
			return ResponseEntity.ok().body(aclist);
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@PutMapping("/updateBalance")
	public ResponseEntity<?> updateBalance(@RequestBody UpdateDto updateDto) {
		try {
			accountHolderService.getByAccountId(updateDto.getAccountholderId());
			int aid=accountDetailsService.getByAccountId(updateDto.getAccountholderId());
			System.out.println(aid);
			Account account=new Account();
			if(account.getId()==aid) {
			if(updateDto.getBalance()!=null)
				account.setBalance(updateDto.getBalance());;
			}
			account.setRateOfInterest(account.getRateOfInterest());
			account.setType(account.getType());
			account=accountService.postAccount(account);
			//returning
			return ResponseEntity.ok().body(account);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	} 
}
