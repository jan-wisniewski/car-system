package com.app.ui.menu;

import com.app.persistence.enums.CarBodyType;
import com.app.persistence.enums.EngineType;
import com.app.service.enums.SortCriterion;
import com.app.service.enums.StatsCriterion;
import com.app.service.service.CarsService;
import com.app.ui.user_data.UserDataService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class MenuService {

    private final CarsService carsService;

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("1. Show all cars");
                System.out.println("2. Sort cars");
                System.out.println("3. Cars with specific body nad price range");
                System.out.println("4. Sort cars alphabetically with specific Engine Type");
                System.out.println("5. Show stats with specific argument");
                System.out.println("6. Cars and travelled distance");
                System.out.println("7. Tyre types and cars");
                System.out.println("8. Find cars with specific components");
                System.out.println("0. Exit");
                int option = UserDataService.getInteger("Type option");
                switch (option) {
                    case 1 -> option1();
                    case 2 -> option2();
                    case 3 -> option3();
                    case 4 -> option4();
                    case 5 -> option5();
                    case 6 -> option6();
                    case 7 -> option7();
                    case 8 -> option8();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("No option with this number");
                }
            } catch (Exception e) {
                System.out.println("FAILED! " + e.getMessage());
            }
        }
    }

    private void option8() {
        System.out.println("Cars with specific components");
        List<String> components = UserDataService.getList("Type components");
        System.out.println(carsService.findCarsContainingAllComponents(components));
    }

    private void option7() {
        System.out.println("Tyre types and car list");
        System.out.println(carsService.groupByTyreType());
    }

    private void option6() {
        System.out.println("Cars with travelled distance");
        System.out.println(carsService.mapToTravelledDistance());
    }

    private void option5() {
        System.out.println("Stats with specific criterion");
        StatsCriterion criterion = UserDataService.getStatsCriterion();
        System.out.println(carsService.showCarsStats(criterion));
    }

    private void option4() {
        System.out.println("Sorted alphabetically with specific Engine Type");
        EngineType engineType = UserDataService.getEngineType();
        System.out.println(carsService.findByEngineType(engineType));
    }

    private void option3() {
        System.out.println("Cars with specific Car Body Type and Price Range");
        CarBodyType bodyType = UserDataService.getCarBodyType();
        BigDecimal priceFrom = UserDataService.getBigDecimal("Price from?");
        BigDecimal priceTo = UserDataService.getBigDecimal("Price to?");
        System.out.println(carsService.findByCarBodyTypeAndPriceRange(bodyType, priceFrom, priceTo));
    }

    private void option1() {
        System.out.println("Showing all cars");
        System.out.println(carsService);
    }

    private void option2() {
        SortCriterion sortCriterion = UserDataService.getSortCriterion();
        boolean ascending = UserDataService.getBoolean("Ascending? [y/n]");
        System.out.println(carsService.sortCars(sortCriterion, ascending));
    }
}
