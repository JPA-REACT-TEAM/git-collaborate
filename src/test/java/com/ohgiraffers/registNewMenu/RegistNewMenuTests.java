package com.ohgiraffers.registNewMenu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistNewMenuTests {

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
        entityManager.clear();
    }

    @Test
    public void regist_new_menu_test() {

        RegistMenu menu = new RegistMenu();
        menu.setCategoryCode(1); // 식사
        menu.setMenuCode(37);
        menu.setMenuName("참치꽁치조림1");
        menu.setMenuPrice(40000);
        menu.setOrderable_status("Y");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(menu);

        entityTransaction.commit();

        RegistMenu foundMenu = entityManager.find(RegistMenu.class, 37);

        assertEquals(37, foundMenu.getMenuCode());

    }

    @Test
    public void delete_menu_test() {
    }
}
