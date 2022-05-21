package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleDao dao;

    @Autowired
    public RoleServiceImp(RoleDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Role> getList() {
        return dao.getList();
    }

    @Override
    public List<Role> getListByIds(List<Long> id) {
        return dao.getListByIds(id);
    }
}
