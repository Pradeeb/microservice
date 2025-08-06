package com.vc.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vc.dashboard.entity.CardDetails;

@Repository
public interface IDashpordRepo extends JpaRepository<CardDetails, Long> {

	CardDetails findByEmployeeId(String empolyeId);

}
