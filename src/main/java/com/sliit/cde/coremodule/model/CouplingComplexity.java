package com.sliit.cde.coremodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "coupling_complexity")
@EntityListeners(AuditingEntityListener.class)
public class CouplingComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
