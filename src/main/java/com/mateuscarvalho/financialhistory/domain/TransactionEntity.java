package com.mateuscarvalho.financialhistory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.TransactionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transactionType")
    private TransactionType transactionType;

    @Column(name = "date")
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "depositor_id", nullable = false)
    private AccountEntity depositor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "favored_id", nullable = false)
    private AccountEntity favored;

    @Column(name = "value")
    private Double value;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, TransactionType transactionType, LocalDateTime localDateTime,
            AccountEntity depositor, AccountEntity favored, Double value) {
        this.id = id;
        this.transactionType = transactionType;
        this.localDateTime = localDateTime;
        this.depositor = depositor;
        this.favored = favored;
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

    public AccountEntity getDepositor() {
        return depositor;
    }

    public void setDepositor(AccountEntity depositor) {
        this.depositor = depositor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public AccountEntity getFavored() {
        return favored;
    }

    public void setFavored(AccountEntity favored) {
        this.favored = favored;
    }
}
