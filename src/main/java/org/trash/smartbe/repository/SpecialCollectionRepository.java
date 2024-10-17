package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trash.smartbe.model.SpecialCollection;

import java.util.List;

@Repository
public interface SpecialCollectionRepository extends JpaRepository<SpecialCollection, Long> {
    List<SpecialCollection> findByWasteAccountId(Long wasteAccountId);
}