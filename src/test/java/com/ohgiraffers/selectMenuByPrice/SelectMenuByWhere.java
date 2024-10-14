package com.ohgiraffers.selectMenuByPrice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SelectMenuByWhere {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 가격별_메뉴조회_테스트() {
        // given
        int menuPrice = 5000;

        //when
        String jpql = "SELECT m FROM menu as m WHERE m.menuPrice = :menuPrice";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("menuPrice", menuPrice);

        List<Menu> foundMenuList = query.getResultList();

        // then
        assertNotNull(foundMenuList);
        foundMenuList.forEach(System.out::println);
    }
}
