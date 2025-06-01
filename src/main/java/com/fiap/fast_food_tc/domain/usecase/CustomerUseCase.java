package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.ECustomer;

import java.util.List;

public interface CustomerUseCase {

    ECustomer create(ECustomer user);

    ECustomer update(ECustomer user);

    void delete(Integer id);

    ECustomer getById(Integer id);

    List<ECustomer> getAll();

    ECustomer getByDocumentNumber(String documentNumber);

}
