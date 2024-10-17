package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.CollectionDTO;
import org.trash.smartbe.service.CollectionService;


@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllCollections() {
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getCollectionById(@PathVariable Long id) {
        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @GetMapping("/waste-bin/{wasteBinId}")
    public ResponseEntity<ResponseEntityDto> getCollectionsByWasteBinId(@PathVariable Long wasteBinId) {
        return ResponseEntity.ok(collectionService.getCollectionsByWasteBinId(wasteBinId));
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDto> createCollection(@RequestBody CollectionDTO collectionDTO) {
        return ResponseEntity.ok(collectionService.createCollection(collectionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> updateCollection(@PathVariable Long id, @RequestBody CollectionDTO collectionDTO) {
        return ResponseEntity.ok(collectionService.updateCollection(id, collectionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> deleteCollection(@PathVariable Long id) {
        return ResponseEntity.ok(collectionService.deleteCollection(id));
    }
}
