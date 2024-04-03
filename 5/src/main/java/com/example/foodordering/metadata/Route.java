package com.example.foodordering.metadata;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Route {
    public static final String MENU = "/menu";
    public static final String MENU_ITEM = "/menu/*";

    public static final String CART_ADD = "/cart/add";
}
