package com.jason.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pms_position")
public class PmsPosition {
    @Id
    @Column(name = "phid")
    private Long phid;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name")
    private String name;

}
