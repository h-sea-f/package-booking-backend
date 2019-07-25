package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.Waybill;
import com.oocl.packagebooking.service.WaybillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8088" }, maxAge = 6000)
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
