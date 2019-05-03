package com.askew.sean.mealplanner.model;

public class UnitConversion {
    private static int tsp2floz;
    private static int tsp2tblsp;
    private static int tblsp2floz;
    private static int cup2floz;
    private static int cup2pint;
    private static int pint2floz;
    private static int pint2quart;
    private static int quart2floz;
    private static int quart2liter;
    private static int liter2floz;
    private static int quart2gallon;
    private static int gallon2floz;

    public String tofloz(String fromAmount) {

        float flozAmount = getCurrentMeasurement(fromAmount);
        return (Float.toString(flozAmount) + " floz");
    }
    private float getCurrentMeasurement(String fromAmount){
        fromAmount = fromAmount.trim();
        fromAmount = fromAmount.toLowerCase();
     String[] fromAmountArray =  fromAmount.split(" ");
     String unitOfMeasureString = fromAmountArray[fromAmount.length()-1];
     UnitsOfMeasure unitsOfMeasure = UnitsOfMeasure.valueOf(unitOfMeasureString);
     float conversionRatio =1;

     switch(unitsOfMeasure) {
         case FLOZ:
             conversionRatio = 1;
             break;
         case TBLSP:
             conversionRatio = tblsp2floz;
             break;
         case PINT:
             conversionRatio = pint2floz;
             break;
         case QUART:
             conversionRatio = quart2floz;
             break;
         case LITER:
             conversionRatio = liter2floz;
             break;
         case GALLON:
             conversionRatio = gallon2floz;
             break;
     }
     return Integer.decode(fromAmountArray[0]) * conversionRatio;
    }
    public enum UnitsOfMeasure{
        FLOZ,
        TSP,
        TBLSP,
        CUP,
        PINT,
        LITER,
        QUART,
        GALLON;
    }




}
