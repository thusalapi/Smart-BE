// WasteBinController.java
package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.dto.WasteBinDTO;
import org.trash.smartbe.service.WasteBinService;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;

@RestController
@RequestMapping("/api/waste-bins")
public class WasteBinController {

    @Autowired
    private WasteBinService wasteBinService;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllWasteBins() {
        return ResponseEntity.ok(wasteBinService.getAllWasteBins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getWasteBinById(@PathVariable Long id) {
        return ResponseEntity.ok(wasteBinService.getWasteBinById(id));
    }

    @GetMapping("/bin-number/{binNumber}")
    public ResponseEntity<ResponseEntityDto> getWasteBinByBinNumber(@PathVariable String binNumber) {
        return ResponseEntity.ok(wasteBinService.getWasteBinByBinNumber(binNumber));
    }

    @GetMapping("/waste-account/{wasteAccountId}")
    public ResponseEntity<ResponseEntityDto> getWasteBinsByWasteAccountId(@PathVariable Long wasteAccountId) {
        return ResponseEntity.ok(wasteBinService.getWasteBinsByWasteAccountId(wasteAccountId));
    }

    @GetMapping("/waste-type/{wasteType}")
    public ResponseEntity<ResponseEntityDto> getWasteBinsByWasteType(@PathVariable String wasteType) {
        return ResponseEntity.ok(wasteBinService.getWasteBinsByWasteType(wasteType));
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDto> createWasteBin(@RequestBody WasteBinDTO wasteBinDTO) {
        return ResponseEntity.ok(wasteBinService.createWasteBin(wasteBinDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> updateWasteBin(@PathVariable Long id, @RequestBody WasteBinDTO wasteBinDTO) {
        return ResponseEntity.ok(wasteBinService.updateWasteBin(id, wasteBinDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> deleteWasteBin(@PathVariable Long id) {
        return ResponseEntity.ok(wasteBinService.deleteWasteBin(id));
    }

    @PatchMapping("/{id}/level")
    public ResponseEntity<ResponseEntityDto> updateWasteBinLevel(@PathVariable Long id, @RequestParam Double newLevel) {
        return ResponseEntity.ok(wasteBinService.updateWasteBinLevel(id, newLevel));
    }
}