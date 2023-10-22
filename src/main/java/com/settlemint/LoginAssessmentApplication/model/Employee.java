package com.settlemint.LoginAssessmentApplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String email;
    private String password;
    private String department;
    private String designation;
    private String technology;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employees_role", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
    Set<Role> roles = new HashSet<Role>();
    public void setRole(Role role) {
        this.roles.add(role);
    }
}
