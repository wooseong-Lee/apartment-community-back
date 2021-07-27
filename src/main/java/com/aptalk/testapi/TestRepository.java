package com.aptalk.testapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepository {
    private final EntityManager em;

    public void save(TestUser testUser) {
        em.persist(testUser);
    }

    public TestUser findOneUserById(Long id){
        return em.find(TestUser.class, id);
    }

    public List<TestUser> findAllUser(){
        return em.createQuery("select u from TestUser u", TestUser.class).getResultList();
    }

}
