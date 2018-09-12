package net.lightoze.vaadin_bugs.spring;

class Person {
    private final int id;
    private final String name;
    private final int age;

    Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Person create(int i) {
        return new Person(i, "Person " + i, 10 + i);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
