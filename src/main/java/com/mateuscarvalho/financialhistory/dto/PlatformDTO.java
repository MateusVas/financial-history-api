package com.mateuscarvalho.financialhistory.dto;

import java.util.Set;

public class PlatformDTO {

    private Long id;

    private String name;

    private String module;

    public PlatformDTO() {
    }

    public PlatformDTO(Long id, String name, String module) {
        this.id = id;
        this.name = name;
        this.module = module;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
