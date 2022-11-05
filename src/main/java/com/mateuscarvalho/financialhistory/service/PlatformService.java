package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.domain.PlatformEntity;
import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.dto.PlatformDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import com.mateuscarvalho.financialhistory.exception.BadRequestException;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.PlatformMapper;
import com.mateuscarvalho.financialhistory.mapper.UserMapper;
import com.mateuscarvalho.financialhistory.repository.PlatformRepository;
import com.mateuscarvalho.financialhistory.repository.UserRepository;
import enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class PlatformService {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    PlatformMapper platformMapper;

    public PlatformDTO findById(Long id) {
        return platformMapper.platformToDto(platformRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Platform not found!")));
    }

    public Page<PlatformDTO> findAll(Pageable pageable) {
        return platformMapper.platformToDto(platformRepository.findAll(pageable));
    }

    public Page<PlatformDTO> search(Map<String, String> requestParams) {
        final PageRequest pageRequest = !requestParams.keySet().isEmpty() ?
                PageRequest.of(Integer.parseInt(requestParams.get("page")),
                        Integer.valueOf(requestParams.get("size"))) : PageRequest.of(1, 10);

        final String name = requestParams.get("name");
        final String module = requestParams.get("module");
        return platformMapper.platformToDto(
                platformRepository.findByNameAndModuleAndActiveIsTrue(pageRequest, name, module));
    }

    public PlatformDTO save(PlatformDTO platformDTO) {
        final PlatformEntity saved =
                platformRepository.save(platformMapper.dtoToPlatform(platformDTO));
        return platformMapper.platformToDto(saved);
    }

    public void update(PlatformDTO platformDTO) {
        if (platformDTO.getId() == null) {
            throw new BadRequestException("Platform without id");
        }
        platformRepository.save(platformMapper.dtoToPlatform(platformDTO));
    }

    public void deleteById(Long id) {
        platformRepository.deleteById(id);
    }
}
