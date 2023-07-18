package com.example.springschool.constant;

public enum Gender {
    MALE(1),
    FEMALE(2),
    OTHER(3);

    public int val;

    Gender(int gender) {
        this.val = gender;
    }
}
