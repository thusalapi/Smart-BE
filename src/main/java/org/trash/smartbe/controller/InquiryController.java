package org.trash.smartbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.InquiryDTO;
import org.trash.smartbe.service.InquiryService;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping
    public ResponseEntityDto createInquiry(@RequestBody InquiryDTO inquiryDTO) {
        return inquiryService.createInquiry(inquiryDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntityDto getInquiryById(@PathVariable Long id) {
        return inquiryService.getInquiryById(id);
    }

    @GetMapping
    public ResponseEntityDto getAllInquiries() {
        return inquiryService.getAllInquiries();
    }

    @PutMapping("/{id}")
    public ResponseEntityDto updateInquiry(@PathVariable Long id, @RequestBody InquiryDTO inquiryDTO) {
        return inquiryService.updateInquiry(id, inquiryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntityDto deleteInquiry(@PathVariable Long id) {
        return inquiryService.deleteInquiry(id);
    }
}