package com.ohgiraffers.selectMenuByPrice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "menu")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;
    @Column(name = "category_code")
    private int categoryCode;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_price")
    private String menuPrice;
    @Column(name = "orderable_status")
    private String orderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, int categoryCode, String menuName, String menuPrice, String orderableStatus) {
        this.menuCode = menuCode;
        this.categoryCode = categoryCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", categoryCode=" + categoryCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
