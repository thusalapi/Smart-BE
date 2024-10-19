// ResidentController.java
package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.ResidentDTO;
import org.trash.smartbe.service.ResidentService;

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

    @PostMapping("/register")
    public ResponseEntityDto registerResident(@RequestBody ResidentDTO residentDTO, Authentication authentication) {
        String username = authentication.getName();
        return residentService.registerResident(username, residentDTO);
    }
}