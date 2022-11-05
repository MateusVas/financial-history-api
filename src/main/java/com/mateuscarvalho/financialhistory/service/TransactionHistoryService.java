package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.domain.TransactionEntity;
import com.mateuscarvalho.financialhistory.domain.TransactionHistoryEntity;
import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionHistoryDTO;
import com.mateuscarvalho.financialhistory.exception.BadRequestException;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.TransactionHistoryMapper;
import com.mateuscarvalho.financialhistory.mapper.TransactionMapper;
import com.mateuscarvalho.financialhistory.repository.TransactionHistoryRepository;
import com.mateuscarvalho.financialhistory.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    TransactionHistoryMapper transactionHistoryMapper;

    public TransactionHistoryDTO findById(Long id) {
        return transactionHistoryMapper.transactionHistoryToDto(
                transactionHistoryRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Transaction not found!")));
    }

    public Page<TransactionHistoryDTO> findAll(Pageable pageable) {
        return transactionHistoryMapper.transactionHistoryToDto(
                transactionHistoryRepository.findAll(pageable));
    }

    public void save(TransactionDTO transactionDTO) {
        transactionHistoryRepository.save(transactionHistoryMapper.convertToEntity(transactionDTO));
    }
}
