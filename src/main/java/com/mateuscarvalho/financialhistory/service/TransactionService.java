package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.domain.TransactionEntity;
import com.mateuscarvalho.financialhistory.dto.AccountDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.exception.BadRequestException;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.TransactionMapper;
import com.mateuscarvalho.financialhistory.repository.TransactionRepository;
import enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionHistoryService transactionHistoryService;

    @Autowired
    TransactionMapper transactionMapper;

    @Value("${async.milliseconds:1000}")
    private Long asyncMilliseconds;

    public TransactionDTO findById(Long id) {
        return transactionMapper.transactionToDto(transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found!")));
    }

    public Page<TransactionDTO> findAll(Pageable pageable) {
        return transactionMapper.transactionToDto(transactionRepository.findAll(pageable));
    }

    @Transactional
    public TransactionDTO save(TransactionDTO transactionDTO) {
        try {
            transactionDTO.setLocalDateTime(LocalDateTime.now());
            final TransactionDTO saved = transactionMapper.transactionToDto(
                    transactionRepository.save(transactionMapper.dtoToTransaction(transactionDTO)));
            accountService.deposit(saved.getFavored().getId(), saved.getValue());
            transactionHistoryService.save(saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("The related entity does not exist.");
        }
    }
}
