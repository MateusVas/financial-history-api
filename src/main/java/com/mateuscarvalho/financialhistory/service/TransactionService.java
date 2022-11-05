package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.exception.BadRequestException;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.TransactionMapper;
import com.mateuscarvalho.financialhistory.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class TransactionService {

    private final Logger logger = Logger.getLogger(TransactionService.class.getName());

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionHistoryService transactionHistoryService;

    @Autowired
    TransactionMapper transactionMapper;

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
            logger.log(Level.INFO, "Transaction successful!!");
            return saved;
        } catch (DataIntegrityViolationException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new BadRequestException("The related entity does not exist.");
        }
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
