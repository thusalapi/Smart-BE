// ResidentController.java
package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.dto.ResidentDTO;
import org.trash.smartbe.service.ResidentService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllResidents() {
        return ResponseEntity.ok(residentService.getAllResidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getResidentById(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getResidentById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseEntityDto> getResidentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(residentService.getResidentByEmail(email));
    }

    @GetMapping("/waste-account/{wasteAccountId}")
    public ResponseEntity<ResponseEntityDto> getResidentByWasteAccountId(@PathVariable Long wasteAccountId) {
        return ResponseEntity.ok(residentService.getResidentByWasteAccountId(wasteAccountId));
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDto> createResident(@RequestBody ResidentDTO residentDTO) {
        return ResponseEntity.ok(residentService.createResident(residentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> updateResident(@PathVariable Long id, @RequestBody ResidentDTO residentDTO) {
        return ResponseEntity.ok(residentService.updateResident(id, residentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> deleteResident(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.deleteResident(id));
    }
}