package com.mateuscarvalho.financialhistory.mapper;

import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserEntity dtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO userToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public Page<UserDTO> userToDto(Page<UserEntity> users) {
        return new PageImpl<>(users.stream().map(this::userToDto).collect(Collectors.toList()));
    }
}
