package com.mateuscarvalho.financialhistory.controller;

import com.mateuscarvalho.financialhistory.dto.PlatformDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import com.mateuscarvalho.financialhistory.service.PlatformService;
import com.mateuscarvalho.financialhistory.service.UserService;
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
import java.util.Map;

@RestController
@RequestMapping("platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping("/{id}")
    public ResponseEntity<PlatformDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(platformService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PlatformDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(platformService.findAll(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PlatformDTO>> search(
            @RequestParam Map<String, String> requestParams) {
        return ResponseEntity.ok(platformService.search(requestParams));
    }

    @PostMapping()
    public ResponseEntity<PlatformDTO> save(@RequestBody PlatformDTO platformDTO) {
        return new ResponseEntity<>(platformService.save(platformDTO), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody PlatformDTO platformDTO) {
        platformService.update(platformDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        platformService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
