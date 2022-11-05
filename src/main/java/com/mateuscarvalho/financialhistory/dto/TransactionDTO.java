package com.mateuscarvalho.financialhistory.dto;

import enums.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;

    private TransactionType transactionType;

    private LocalDateTime localDateTime;

    private AccountDTO depositor;

    private AccountDTO favored;

    private Double value;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, TransactionType transactionType, LocalDateTime localDateTime,
            AccountDTO depositor, AccountDTO favored, Double value) {
        this.id = id;
        this.transactionType = transactionType;
        this.localDateTime = localDateTime;
        this.favored = favored;
        this.depositor = depositor;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public AccountDTO getDepositor() {
        return depositor;
    }

    public void setDepositor(AccountDTO depositor) {
        this.depositor = depositor;
    }

    public AccountDTO getFavored() {
        return favored;
    }

    public void setFavored(AccountDTO favored) {
        this.favored = favored;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
