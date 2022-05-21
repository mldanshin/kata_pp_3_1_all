package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getList() {
        return entityManager.createQuery("SELECT role FROM Role role", Role.class)
                .getResultList();
    }

    @Override
    public List<Role> getListByIds(List<Long> id) {
        return entityManager.createQuery("SELECT role FROM Role role WHERE role.id IN :id", Role.class)
                .setParameter("id", id)
                .getResultList();
    }
}
