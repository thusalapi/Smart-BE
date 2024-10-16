package org.trash.smartbe.repository;

import org.trash.smartbe.model.Bill;

import java.util.List;

public interface BillRepository {

    List<Bill> findByAmountBetween(float minAmount, float maxAmount);

}
