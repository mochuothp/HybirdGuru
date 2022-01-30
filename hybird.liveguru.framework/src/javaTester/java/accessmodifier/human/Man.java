package java.accessmodifier.human;

import java.accessmodifier.animals.Dog;

public class Man extends Dog {
    public Man() {
        System.out.println(getAge());
    }

    public static void main(String[] args)
    {
        Man man = new Man();
    }

}
