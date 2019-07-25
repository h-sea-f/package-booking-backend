package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.Waybill;
import com.oocl.packagebooking.respository.WaybillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaybillService {
    @Autowired
    WaybillRepository waybillRepository;
    public Waybill save(Waybill waybill) {
        return waybillRepository.save(waybill);
    }

    public List<Waybill> getAll() {
        return waybillRepository.findAll();
    }
}
