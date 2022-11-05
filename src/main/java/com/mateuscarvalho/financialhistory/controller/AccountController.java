package com.mateuscarvalho.financialhistory.controller;

import com.mateuscarvalho.financialhistory.dto.AccountDTO;
import com.mateuscarvalho.financialhistory.dto.PlatformDTO;
import com.mateuscarvalho.financialhistory.service.AccountService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.save(accountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/deposit/{id}/{value}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @PathVariable Double value) {
        accountService.deposit(id, value);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        accountService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
