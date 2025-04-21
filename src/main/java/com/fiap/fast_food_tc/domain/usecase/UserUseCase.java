package com.fiap.fast_food_tc.domain.usecase;

import com.fiap.fast_food_tc.domain.entity.EUser;

import java.util.List;

public interface UserUseCase {

    EUser create(EUser user);

    void update(EUser user);

    void delete(Long id);

    EUser getById(Long id);

    List<EUser> getAll();

}
