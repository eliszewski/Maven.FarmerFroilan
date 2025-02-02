package com.zipcodewilmington.froilansfarm.WeekOnTheFarm;

import com.zipcodewilmington.froilansfarm.*;
import com.zipcodewilmington.froilansfarm.Shelter.FarmHouse;
import com.zipcodewilmington.froilansfarm.Shelter.Field;
import com.zipcodewilmington.froilansfarm.Shelter.Stable;
import org.junit.Assert;
import org.junit.Test;
public class MondayTest {
    @Test
    public void TestDay() {
        MainApplication run = new MainApplication();
        Farm farm = run.setUpFarm();
        FarmHouse farmHouse = farm.getFarmhouse();
        Farmer froilan = (Farmer) farmHouse.get(0);
        Pilot froilanda = (Pilot) farmHouse.get(1);
        Edible[] froilanRation = {new EarCorn(), new TomatoPlant(), new TomatoPlant(),
                new EdibleEgg(), new EdibleEgg(), new EdibleEgg(), new EdibleEgg(), new EdibleEgg()};
        Edible[] froilandaRation = {new EarCorn(), new EarCorn(), new TomatoPlant(),
                new EdibleEgg(), new EdibleEgg()};
        EarCorn[] horseRation = {new EarCorn(), new EarCorn(), new EarCorn()};
        farmHouse.feedHouseMember(froilan, froilanRation);
        farmHouse.feedHouseMember(froilanda, froilandaRation);
        for (Stable stable : farm.getStables()) stable.feedHorses(horseRation);
        for (Stable stable : farm.getStables()) {
            for (Horse horse : stable) {
                froilan.mount(horse);
                froilan.disMount(horse);
                froilanda.mount(horse);
                froilanda.disMount(horse);
            }
        }
        CropDuster cropDuster = (CropDuster) farm.getVehicles().get(0);
        froilanda.mount(cropDuster);
        System.out.println("Has Froilanda mounted the cropduster? " + cropDuster.isMounted());

        Field field = farm.getField();
        for (int i = 0; i < field.getCropRows().size(); i++) {
            Croprow cropRow = (Croprow) field.getCropRows().get(i);
            cropDuster.fertilize(cropRow);
            System.out.println("Have the crops in crop row "+ Integer.valueOf(i+1) +" been fertilized? " + cropRow.isFertilized());
        }
        froilanda.disMount(cropDuster);
        System.out.println("Is Froilanda still on the cropduster? " + cropDuster.isMounted());

        //then test Farmhouse was fed
        Assert.assertArrayEquals(froilanRation,froilan.getFoodEaten().toArray());
        Assert.assertArrayEquals(froilandaRation,froilanda.getFoodEaten().toArray());
        //test horses were fed
        for (Stable stable: farm.getStables()
        ) {
            for (Horse horse: stable
            ) {
                Assert.assertArrayEquals(horseRation,horse.getFoodEaten().toArray());
            }
        }
        //test horses were rode
        for (Stable stable: farm.getStables()
        ) {
            for (Horse horse: stable
            ) {
                //Assert.assertTrue(horse.isMounted());
            }
        }
    }
}