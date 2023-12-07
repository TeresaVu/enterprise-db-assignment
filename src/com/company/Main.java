package com.company;

public class Main {

    public static void main(String[] args) {
        InMemoryDB inMemoryDB = new InMemoryDB();

        System.out.println(inMemoryDB.get("A")); // should return null

        try {
            inMemoryDB.put("A", 5); // should throw an error
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        inMemoryDB.begin_transaction();
        inMemoryDB.put("A", 5);
        System.out.println(inMemoryDB.get("A")); // should return null because updates not committed yet

        inMemoryDB.put("A", 6);
        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("A")); // should return 6

        try {
            inMemoryDB.commit(); // should throw an error
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            inMemoryDB.rollback(); // should throw an error
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(inMemoryDB.get("B")); // should return null

        inMemoryDB.begin_transaction();
        inMemoryDB.put("B", 10);
        inMemoryDB.rollback();
        System.out.println(inMemoryDB.get("B")); // should return null
    }
}
