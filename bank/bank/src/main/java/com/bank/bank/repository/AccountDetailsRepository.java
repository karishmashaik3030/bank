package com.bank.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.bank.model.AccountDetails;
import com.bank.bank.model.AccountHolder;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer>{

	List<AccountHolder> findByExecutiveId(int eid);

	AccountDetails findByAccountHolderId(int accountholderId);
    @Query("select ac.accountHolder.id from AccountDetails ac where ac.executive.id=?1")
	List<Integer> getHolderId(int eid);
    @Query("select ac.accountHolder.id from AccountDetails ac where ac.account.id=?1")
	List<Integer> getAccountHolderId(int aid);
    @Query("select ac.account.id from AccountDetails ac where ac.accountHolder.id=?1")
	int getByAccountId(int accountholderId);

}
