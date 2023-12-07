package com.company;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {
    private Map<String, Integer> data;
    private Map<String, Integer> transactionData;
    private boolean inTransaction;

    public InMemoryDB() {
        this.data = new HashMap<>();
        this.transactionData = new HashMap<>();
        this.inTransaction = false;
    }

    public void begin_transaction() {
        if (inTransaction) {
            throw new IllegalStateException("Transaction already in progress");
        }
        inTransaction = true;
    }

    public void put(String key, int value) {
        if (!inTransaction) {
            throw new IllegalStateException("Transaction not in progress");
        }
        transactionData.put(key, value);
    }

    public Integer get(String key) {
        return data.get(key);
    }

    public void commit() {
        if (!inTransaction) {
            throw new IllegalStateException("No open transaction to commit");
        }

        data.putAll(transactionData);
        clearTransaction();
    }

    public void rollback() {
        if (!inTransaction) {
            throw new IllegalStateException("No ongoing transaction to rollback");
        }

        clearTransaction();
    }

    private void clearTransaction() {
        transactionData.clear();
        inTransaction = false;
    }
}
