package com.ohgiraffers.DeleteMenu;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNull;

public class deleteMenuTests {
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
    public void delete_menu_test() {
        int menuCode = 1;

        String query = "delete from tbl_menu where menu_code = ?";

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query1 = entityManager.createNativeQuery(query);
        query1.setParameter(1, menuCode);
        query1.executeUpdate();

        entityTransaction.commit();

        Menu foundMenu = entityManager.find(Menu.class, 1);

        assertNull(foundMenu);

    }
}
