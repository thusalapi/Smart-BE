package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}