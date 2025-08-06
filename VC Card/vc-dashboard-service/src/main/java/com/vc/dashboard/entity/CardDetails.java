package com.vc.dashboard.entity;

import java.util.Date;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carddetails")
@Data
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    private String distictiveCode;

    private String name;

    private String designation;

    private String areacodeB;

    private String phoneB;

    private String areacodeD;

    private String phoneD;

    private String phoneM;

    private String areacodeF;

    private String phoneF;

    private String email;

    private String approverId;

    private String approvingName;

    private String approvalEmailId;

    private String costCenter;

    private String requestno;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "uploadeddate", columnDefinition = "DATETIME")
    private Date uploadedDate;

    private Integer customerId;
}
