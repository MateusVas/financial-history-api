package com.mateuscarvalho.financialhistory.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "platform")
public class PlatformEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "module")
    private String module;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @OneToMany(mappedBy = "platform", fetch = FetchType.LAZY)
    private Set<AccountEntity> accountEntity;

    public PlatformEntity() {
    }

    public PlatformEntity(Long id, String name, String module, boolean active) {
        this.id = id;
        this.name = name;
        this.module = module;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<AccountEntity> getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(Set<AccountEntity> accountEntity) {
        this.accountEntity = accountEntity;
    }
}
