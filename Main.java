import java.util.ArrayList;
import java.util.List;

// Интерфейс ТорговыйАвтомат
interface VendingMachine {
    HotDrink getProduct(String name, int volume);
    HotDrink getProduct(String name, int volume, int temperature);
}

// Базовый класс Напиток
class Drink {
    private String name;
    private int volume;

    public Drink(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Напиток: " + name + ", объем: " + volume + " мл";
    }
}

// Класс ГорячийНапиток, наследующий от Drink
class HotDrink extends Drink {
    public HotDrink(String name, int volume) {
        super(name, volume);
    }

    @Override
    public String toString() {
        return "Горячий напиток: " + getName() + ", объем: " + getVolume() + " мл";
    }
}

// Класс ГорячийНапитокСТемпературой, наследующий от HotDrink
class HotDrinkWithTemperature extends HotDrink {
    private int temperature;

    public HotDrinkWithTemperature(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Горячий напиток: " + getName() + ", объем: " + getVolume() + " мл, температура: " + temperature + "°C";
    }
}

// Класс ГорячихНапитковАвтомат, реализующий интерфейс VendingMachine
class HotDrinkVendingMachine implements VendingMachine {
    private List<HotDrinkWithTemperature> hotDrinks;

    public HotDrinkVendingMachine() {
        this.hotDrinks = new ArrayList<>();
    }

    // Метод для добавления напитка в автомат
    public void addDrink(HotDrinkWithTemperature drink) {
        hotDrinks.add(drink);
    }

    // Реализация метода для поиска по имени и объему
    @Override
    public HotDrink getProduct(String name, int volume) {
        for (HotDrinkWithTemperature drink : hotDrinks) {
            if (drink.getName().equalsIgnoreCase(name) && drink.getVolume() == volume) {
                return drink;
            }
        }
        System.out.println("Напиток не найден.");
        return null;
    }

    // Перегруженный метод для поиска по имени, объему и температуре
    @Override
    public HotDrink getProduct(String name, int volume, int temperature) {
        for (HotDrinkWithTemperature drink : hotDrinks) {
            if (drink.getName().equalsIgnoreCase(name) && drink.getVolume() == volume && drink.getTemperature() == temperature) {
                return drink;
            }
        }
        System.out.println("Напиток с указанными параметрами не найден.");
        return null;
    }
}

// Основной класс с логикой программы
public class Main {
    public static void main(String[] args) {
        // Создаем автомат
        HotDrinkVendingMachine vendingMachine = new HotDrinkVendingMachine();

        // Создаем несколько горячих напитков с температурой
        HotDrinkWithTemperature coffee = new HotDrinkWithTemperature("Кофе", 250, 85);
        HotDrinkWithTemperature tea = new HotDrinkWithTemperature("Чай", 300, 90);
        HotDrinkWithTemperature cappuccino = new HotDrinkWithTemperature("Капучино", 200, 80);

        // Добавляем напитки в автомат
        vendingMachine.addDrink(coffee);
        vendingMachine.addDrink(tea);
        vendingMachine.addDrink(cappuccino);

        // Пример работы автомата
        System.out.println("Получение напитка Кофе с объемом 250 мл и температурой 85°C:");
        HotDrink drink1 = vendingMachine.getProduct("Кофе", 250, 85);
        if (drink1 != null) {
            System.out.println(drink1);
        }

        System.out.println("\nПолучение напитка Чай с объемом 300 мл и температурой 85°C (такого нет):");
        HotDrink drink2 = vendingMachine.getProduct("Чай", 300, 85);
        if (drink2 != null) {
            System.out.println(drink2);
        }

        System.out.println("\nПолучение напитка Капучино с объемом 200 мл:");
        HotDrink drink3 = vendingMachine.getProduct("Капучино", 200);
        if (drink3 != null) {
            System.out.println(drink3);
        }
    }
}

