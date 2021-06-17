package com.learning.mockito;

public interface DM {
    int contract();

    default int default_contract() {
        return contract() + 1;
    }
}
