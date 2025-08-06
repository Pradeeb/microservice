package com.vc.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vc.dashboard.entity.CardDetails;
import com.vc.dashboard.repository.IDashpordRepo;

@Service
public class DashboardService {

	@Autowired IDashpordRepo dashboardRepo;
	
	public CardDetails getUserDetails(String empolyeId) {
		return dashboardRepo.findByEmployeeId(empolyeId);
	}
}
