package com.mateuscarvalho.financialhistory.controller;

import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionHistoryDTO;
import com.mateuscarvalho.financialhistory.service.TransactionHistoryService;
import com.mateuscarvalho.financialhistory.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("transaction-history")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistoryDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionHistoryService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TransactionHistoryDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(transactionHistoryService.findAll(pageable));
    }
}
