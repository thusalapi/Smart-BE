package org.trash.smartbe.service;

import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.InquiryDTO;

public interface InquiryService {
    ResponseEntityDto createInquiry(InquiryDTO inquiryDTO);
    ResponseEntityDto getInquiryById(Long id);
    ResponseEntityDto getAllInquiries();
    ResponseEntityDto updateInquiry(Long id, InquiryDTO inquiryDTO);
    ResponseEntityDto deleteInquiry(Long id);
}