package com.ohgiraffers.updateMenu;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class updateMenuTests {

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
//    @Transactional
    public void updateMenu() {

        // given
        String oldMenuName = "한우딸기국밥";
        String newMenuName = "제주흑돼지국밥";

        // when
        String jpql = "UPDATE menu_updateMenu m SET m.menuName = :newName WHERE m.menuName = :oldName";

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query updatedCount = entityManager.createQuery(jpql);
        updatedCount.setParameter("newName", newMenuName);
        updatedCount.setParameter("oldName", oldMenuName);
        updatedCount.executeUpdate();  // 업데이트 쿼리 실행

        entityTransaction.commit();

        // then
        System.out.println("업데이트된 행 수: " + updatedCount);
//        assert updatedCount > 0;  // 업데이트가 성공적으로 이루어졌는지 검증
    }
}


