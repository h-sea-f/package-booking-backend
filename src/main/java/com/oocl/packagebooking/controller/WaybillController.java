package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.Waybill;
import com.oocl.packagebooking.service.WaybillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WaybillController {
    @Autowired
    WaybillService service;

    @PostMapping("/waybills")
    public Waybill save(@RequestBody Waybill waybill) {
        return service.save(waybill);
    }

    @GetMapping("/waybills")
    public List<Waybill> getAll() {
        return service.getAll();
    }

    @PostMapping("/waybills/{id}")
    public Waybill finished(@PathVariable String id) {
        return service.finished(id);
    }

    @PatchMapping("/waybills/{id}")
    public Waybill ordered(@PathVariable String id, @RequestBody Waybill waybill) {
        return service.ordered(id, waybill);
    }

}
