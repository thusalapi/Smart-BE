package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.InquiryDTO;
import org.trash.smartbe.model.Inquiry;
import org.trash.smartbe.model.Notification;
import org.trash.smartbe.model.User;
import org.trash.smartbe.repository.InquiryRepository;
import org.trash.smartbe.repository.NotificationRepository;
import org.trash.smartbe.repository.UserRepository;
import org.trash.smartbe.service.InquiryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public ResponseEntityDto createInquiry(InquiryDTO inquiryDTO) {
        User user = userRepository.findById(inquiryDTO.getUserId())
                .orElse(null);
        if (user == null) {
            return new ResponseEntityDto("User not found", true);
        }

        Inquiry inquiry = new Inquiry();
        inquiry.setSubject(inquiryDTO.getSubject());
        inquiry.setMessage(inquiryDTO.getMessage());
        inquiry.setStatus("PENDING");
        inquiry.setUser(user);

        Inquiry savedInquiry = inquiryRepository.save(inquiry);

        // Create notification
        Notification notification = new Notification();
        notification.setMessage("New inquiry added: " + inquiry.getSubject());
        notificationRepository.save(notification);

        return new ResponseEntityDto(false, convertToDTO(savedInquiry));
    }

    @Override
    public ResponseEntityDto getInquiryById(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);
        if (inquiry == null) {
            return new ResponseEntityDto("Inquiry not found", true);
        }
        return new ResponseEntityDto(false, convertToDTO(inquiry));
    }

    @Override
    public ResponseEntityDto getAllInquiries() {
        List<InquiryDTO> inquiries = inquiryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntityDto(false, inquiries);
    }

    @Override
    public ResponseEntityDto updateInquiry(Long id, InquiryDTO inquiryDTO) {
        Inquiry existingInquiry = inquiryRepository.findById(id).orElse(null);
        if (existingInquiry == null) {
            return new ResponseEntityDto("Inquiry not found", true);
        }

        existingInquiry.setSubject(inquiryDTO.getSubject());
        existingInquiry.setMessage(inquiryDTO.getMessage());
        existingInquiry.setStatus("CLOSED");

        Inquiry updatedInquiry = inquiryRepository.save(existingInquiry);
        return new ResponseEntityDto(false, convertToDTO(updatedInquiry));
    }

    @Override
    public ResponseEntityDto deleteInquiry(Long id) {
        if (!inquiryRepository.existsById(id)) {
            return new ResponseEntityDto("Inquiry not found", true);
        }
        inquiryRepository.deleteById(id);
        return new ResponseEntityDto("Inquiry deleted successfully", false);
    }

    private InquiryDTO convertToDTO(Inquiry inquiry) {
        InquiryDTO dto = new InquiryDTO();
        dto.setId(inquiry.getId());
        dto.setSubject(inquiry.getSubject());
        dto.setMessage(inquiry.getMessage());
        dto.setCreatedAt(inquiry.getCreatedAt().toString());
        dto.setUserId(inquiry.getUser().getId());
        return dto;
    }
}