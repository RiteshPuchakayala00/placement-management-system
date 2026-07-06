package com.pms.entity;

import com.pms.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @OneToMany(mappedBy = "company")
    private List<Opportunity> opportunities = new ArrayList<>() ;

    @OneToMany(mappedBy = "company")
    private List<Drive> drives = new ArrayList<>();

    @Column(nullable = false)
    private String companyName;

    private String website;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus approvalStatus ;

    private LocalDateTime createdAt ;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now() ;

        if(approvalStatus == null){
            approvalStatus = ApprovalStatus.PENDING;
        }
    }

}
