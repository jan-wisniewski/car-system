package com.app.ui;

import com.app.service.service.CarsService;
import com.app.ui.menu.MenuService;

public class App {
    public static void main(String[] args) {
        final String FILENAME = "./resources/data/cars.json";
        var carsService = new CarsService(FILENAME);
        var menuService = new MenuService(carsService);
        menuService.mainMenu();
    }
}
