package com.blog.socialshare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    private Integer id;
    @Column(nullable = false)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
    }
}
