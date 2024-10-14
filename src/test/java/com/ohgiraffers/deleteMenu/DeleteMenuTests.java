package com.ohgiraffers.deleteMenu;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteMenuTests {

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
    public void 메뉴코드로_메뉴삭제_기능() {

        // given
        int menuCode = 1;

        // when
        String jpql = "DELETE FROM menu m WHERE m.menuCode = :menuCode";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("menuCode", menuCode);
        int deleteMenu = query.executeUpdate();

        // then
        System.out.println("삭제된 행 수: " + deleteMenu);

        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        assertNull(foundMenu);
    }
}
