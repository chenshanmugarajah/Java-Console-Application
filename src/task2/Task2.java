package task2;

import java.util.*;
import java.io.*;

public class Task2 {
    //INITIALIZERS
    boolean exit;
    ArrayList<Predator> predators = new ArrayList<Predator>();
    int currentPlace = -1;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Task2 task2 = new Task2();
        task2.processFile();
        task2.runMenu();
    }
    
    //RUNS THE APPLICATION
    public void runMenu() {
        while (!exit) {
            printMenu();
            int choice = getChoice();
            performAction(choice);
        }
    }
    
    
    //HANDLES THE USER INPUT
    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Application ended");
                System.out.println("-----------------");
                exit = true;
                break;
            case 1:
                System.out.println(predators.get(0).toString());
                currentPlace = 0;
                break;
            case 2:
                if (currentPlace == (predators.size() - 1)){
                    System.out.println(predators.get(predators.size()-1).toString());
                    break;
                } else {
                    currentPlace++;
                    System.out.println(predators.get(currentPlace).toString());
                    break;
                }
            case 3:
                if (currentPlace == 0 || currentPlace == -1) {
                    System.out.println(predators.get(0).toString());
                    currentPlace = 0;
                    break;
                } else {
                    currentPlace--;
                    System.out.println(predators.get(currentPlace));
                    break;
                }
            case 4:
                int temp = (predators.size() - 1);
                System.out.println(predators.get(temp).toString());
                currentPlace = temp;
                break;
            default:
                System.out.println("Unknown error");
        }
    }
    
    
    //GETS THE USER INPUT
    private int getChoice() {
        Scanner gC = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > 4) {
            try {
                System.out.print("Enter choice:> ");
                choice = Integer.parseInt(gC.nextLine());
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println("Invalid: Please enter an option between 1 - 4 or 0 to exit");
                System.out.println("");
            }
        }
        return choice;
    }
    
    //PRINTS THE MENU
    private void printMenu() {
        System.out.println("First...............1");
        System.out.println("Next................2");
        System.out.println("Previous............3");
        System.out.println("Last................4");
        System.out.println("Exit................0");
        System.out.println("");
    }
    
    //LOADS THE FILE and GETS THE INFORMATION NEEDED
    public void processFile() throws IOException {
        //FILE PATHS AND BUFFERED READER
        String fileLocation = System.getProperty("user.dir");
        String dataPath = fileLocation + File.separator + "predators.txt";
        BufferedReader br = new BufferedReader(new FileReader(dataPath));

        String eachLine = null;
        
        //GOES THROUGH EACH LINE AND GETS INFORMATION
        while ((eachLine = br.readLine()) != null) {
            String[] lineArray = eachLine.split(":");
            String name = lineArray[0];
            String latinName = lineArray[1];
            String weight = lineArray[2];
            String speed = lineArray[3];
            String stealth = lineArray[4];
            String encounter = lineArray[5];
            String killerRating = lineArray[6];
            String fact = lineArray[7];
            
            //CONVERTS STRING VALUES TO RESPECTIVE NUMERICAL VALUE TYPES
            float floatWeight = Float.valueOf(weight);
            float floatSpeed = Float.valueOf(speed);
            int intStealth = Integer.valueOf(stealth);
            int intEncounter = Integer.valueOf(encounter);
            int intKillerRating = Integer.valueOf(killerRating);
            
            //MAKES A NEW PREDATOR OBJECT AND ADDS IT TO PREDACTORS ARRAY
            predators.add(new Predator(name, latinName, floatWeight, floatSpeed, intStealth, intEncounter, intKillerRating, fact));
        }
        Collections.sort(predators);
        br.close();
    }
}

class Predator implements Comparable<Predator> {
    
    //VARIABLES
    private String name;
    private String latinName;
    private float weight;
    private float speed;
    private int stealth;
    private int encounter;
    private int killerRating;
    private String fact;
    
    
    //PREDATOR OBJECT
    public Predator(String name, String latinName, float weight, float speed, int stealth, int encounter, int killerRating, String fact) {
        this.name = name;
        this.latinName = latinName;
        this.weight = weight;
        this.speed = speed;
        this.stealth = stealth;
        this.encounter = encounter;
        this.killerRating = killerRating;
        this.fact = fact;
    }
    
    //GETTERS
    public String getName() {
        return name;
    }

    public String getLatinName() {
        return latinName;
    }

    public float getWeight() {
        return weight;
    }

    public float getSpeed() {
        return speed;
    }

    public int getStealth() {
        return stealth;
    }

    public int getEncounter() {
        return encounter;
    }

    public int getKillerRating() {
        return killerRating;
    }

    public String getFact() {
        return fact;
    }
    
    //TO STRING
    @Override
    public String toString() {
        return "---------------------------------------" + "\nName: " + name + "\nLatin name: " + latinName + "\nWeight: " + weight + " kg" + "\nSpeed: " + speed + " mph" + "\nStealth: " + stealth + "\nEncounter: " + encounter + "\nKiller rating: " + killerRating + "\nInteresting fact:\n" + fact + "\n" + "---------------------------------------\n";
    }
    
    //HASH CODE
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.latinName);
        hash = 29 * hash + Float.floatToIntBits(this.weight);
        hash = 29 * hash + Float.floatToIntBits(this.speed);
        hash = 29 * hash + this.stealth;
        hash = 29 * hash + this.encounter;
        hash = 29 * hash + this.killerRating;
        hash = 29 * hash + Objects.hashCode(this.fact);
        return hash;
    }
    
    //EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Predator other = (Predator) obj;
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        if (Float.floatToIntBits(this.speed) != Float.floatToIntBits(other.speed)) {
            return false;
        }
        if (this.stealth != other.stealth) {
            return false;
        }
        if (this.encounter != other.encounter) {
            return false;
        }
        if (this.killerRating != other.killerRating) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.latinName, other.latinName)) {
            return false;
        }
        if (!Objects.equals(this.fact, other.fact)) {
            return false;
        }
        return true;
    }
    
    
    //COMPARE TO
    @Override
    public int compareTo(Predator p) {
        if (getLatinName().compareTo(p.getLatinName()) > 0) {
            return 1;
        } else if (getLatinName().compareTo(p.getLatinName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
