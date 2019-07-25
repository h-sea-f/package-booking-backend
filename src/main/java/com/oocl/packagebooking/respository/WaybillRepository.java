package com.oocl.packagebooking.respository;

import com.oocl.packagebooking.model.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill,String> {
}
