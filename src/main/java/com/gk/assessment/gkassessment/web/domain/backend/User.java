package com.gk.assessment.gkassessment.web.domain.backend;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;

    @Column
    private String phone;

    @Column
    private String password;

    @CreationTimestamp
    private LocalDateTime auditCreateDate;

    @UpdateTimestamp
    private LocalDateTime auditModifyDate;

    public Long getId() {
	return id;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(final String phone) {
	this.phone = phone;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }


    public LocalDateTime getAuditCreateDate() {
	return auditCreateDate;
    }

    public void setAuditCreateDate(final LocalDateTime auditCreateDate) {
	this.auditCreateDate = auditCreateDate;
    }

    public LocalDateTime getAuditModifyDate() {
	return auditModifyDate;
    }

    public void setAuditModifyDate(final LocalDateTime auditModifyDate) {
	this.auditModifyDate = auditModifyDate;
    }
}
