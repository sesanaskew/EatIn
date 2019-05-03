package com.askew.sean.mealplanner.model;

public enum FoodType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK,
        EMPTY;

        public FoodType FoodTypeEnum(String type) {
                switch (type){
                case "breakfast":
                        return FoodType.BREAKFAST;
                case "lunch":
                        return FoodType.LUNCH;
                case "dinner":
                        return FoodType.DINNER;
                case "snack":
                        return FoodType.SNACK;
                case "empty":
                        return FoodType.EMPTY;
                        default:
                                return null;
                }
        }

}

