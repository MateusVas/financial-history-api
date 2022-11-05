package com.mateuscarvalho.financialhistory.repository;

import com.mateuscarvalho.financialhistory.domain.TransactionEntity;
import com.mateuscarvalho.financialhistory.domain.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository
        extends JpaRepository<TransactionHistoryEntity, Long> {

    TransactionHistoryEntity findByDescriptionIgnoreCase(String description);
}
