package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.entity.StaffAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffAssignmentRepository extends JpaRepository<StaffAssignment,Long> {
    StaffAssignment findByBranchId(Long branchId);
}
