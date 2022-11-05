package com.mateuscarvalho.financialhistory.repository;

import com.mateuscarvalho.financialhistory.domain.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
