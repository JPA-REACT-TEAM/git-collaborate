package com.ohgiraffers.selectMenuByCode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectMenuByCodeTests {

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
    public void findMenuByCode() {

        // when
        String jpql = "SELECT m.menuName FROM menu_selectByCode as m WHERE m.menuCode = 7";
        Query query = entityManager.createQuery(jpql);		// 결과 값의 타입을 명시하지 않음

        Object resultMenuName = query.getSingleResult();	// 결과 값은 최상위 타입인 Object로 반환 된다

        // then
        assertTrue(resultMenuName instanceof String);
        assertEquals("민트미역국", resultMenuName);
    }
}
