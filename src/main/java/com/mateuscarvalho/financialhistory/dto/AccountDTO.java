package com.mateuscarvalho.financialhistory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateuscarvalho.financialhistory.domain.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class AccountDTO {

    private Long id;

    private Double balance;

    private UserDTO holder;

    private PlatformDTO platform;

    private Set<TransactionDTO> transactions = new HashSet<>();

    public AccountDTO() {
    }

    public AccountDTO(Long id, double balance, UserDTO holder, PlatformDTO platform,
            Set<TransactionDTO> transactions) {
        this.id = id;
        this.balance = balance;
        this.holder = holder;
        this.platform = platform;
        this.transactions.addAll(transactions);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getHolder() {
        return holder;
    }

    public void setHolder(UserDTO holder) {
        this.holder = holder;
    }

    public PlatformDTO getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformDTO platform) {
        this.platform = platform;
    }

    @JsonIgnore
    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions.addAll(transactions);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
