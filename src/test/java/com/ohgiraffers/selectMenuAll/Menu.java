package com.ohgiraffers.selectMenuAll;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "menu_selectMenuAll")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    int menuCode;

    @Column(name = "menu_name")
    String menuName;

    @Column(name = "menu_price")
    int menuPrice;

    @Column(name = "category_code")
    int categoryCode;

    @Column(name = "orderable_status")
    String orderable_status;

    public Menu() {

    }

    public Menu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderable_status) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderable_status = orderable_status;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderable_status() {
        return orderable_status;
    }

    public void setOrderable_status(String orderable_status) {
        this.orderable_status = orderable_status;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderable_status='" + orderable_status + '\'' +
                '}';
    }
}
