package com.mateuscarvalho.financialhistory.dto;

import java.time.LocalDateTime;

public class TransactionHistoryDTO {

    private Long id;

    private Long transaction_id;

    private String description;

    private LocalDateTime localDateTime;

    public TransactionHistoryDTO() {
    }

    public TransactionHistoryDTO(Long id, Long transaction_id, String description,
            LocalDateTime localDateTime) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.description = description;
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
