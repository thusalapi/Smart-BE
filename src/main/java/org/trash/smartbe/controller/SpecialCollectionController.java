package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.SpecialCollectionDTO;
import org.trash.smartbe.service.SpecialCollectionService;

@RestController
@RequestMapping("/api/special-collections")
public class SpecialCollectionController {

    @Autowired
    private SpecialCollectionService specialCollectionService;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllSpecialCollections() {
        return ResponseEntity.ok(specialCollectionService.getAllSpecialCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getSpecialCollectionById(@PathVariable Long id) {
        return ResponseEntity.ok(specialCollectionService.getSpecialCollectionById(id));
    }

    @GetMapping("/waste-account/{wasteAccountId}")
    public ResponseEntity<ResponseEntityDto> getSpecialCollectionsByWasteAccountId(@PathVariable Long wasteAccountId) {
        return ResponseEntity.ok(specialCollectionService.getSpecialCollectionsByWasteAccountId(wasteAccountId));
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDto> createSpecialCollection(@RequestBody SpecialCollectionDTO specialCollectionDTO) {
        return ResponseEntity.ok(specialCollectionService.createSpecialCollection(specialCollectionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> updateSpecialCollection(@PathVariable Long id, @RequestBody SpecialCollectionDTO specialCollectionDTO) {
        return ResponseEntity.ok(specialCollectionService.updateSpecialCollection(id, specialCollectionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> deleteSpecialCollection(@PathVariable Long id) {
        return ResponseEntity.ok(specialCollectionService.deleteSpecialCollection(id));
    }
}