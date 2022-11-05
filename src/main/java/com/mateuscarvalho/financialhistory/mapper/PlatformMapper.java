package com.mateuscarvalho.financialhistory.mapper;

import com.mateuscarvalho.financialhistory.domain.PlatformEntity;
import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.dto.PlatformDTO;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PlatformMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PlatformEntity dtoToPlatform(PlatformDTO platformDTO) {
        return modelMapper.map(platformDTO, PlatformEntity.class);
    }

    public PlatformDTO platformToDto(PlatformEntity platformEntity) {
        return modelMapper.map(platformEntity, PlatformDTO.class);
    }

    public Page<PlatformDTO> platformToDto(Page<PlatformEntity> platformEntities) {
        return new PageImpl<>(
                platformEntities.stream().map(this::platformToDto).collect(Collectors.toList()));
    }
}
