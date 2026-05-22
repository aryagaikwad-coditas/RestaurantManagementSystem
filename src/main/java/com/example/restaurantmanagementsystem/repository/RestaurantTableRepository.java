package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.entity.RestaurantTable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    boolean existByBranchIdAndStatus(@NotNull(message = "Branch Id is required") Long branchId, @NotNull(message = "Table Number is required ") Long tableNumber);


    RestaurantTable findByBranchIdAndStatus(Long branchId, String status);

    @Transactional
    @Modifying
    @Query("""
            UPDATE RestaurantTable t
            SET  t.assignedWaiter.id = :coveringWaiterId
            WHERE t.branch.id = :branchId
""")
    Object reassign(Long branchId, Long absentWaiterId, Long coveringWaiterId);
}
