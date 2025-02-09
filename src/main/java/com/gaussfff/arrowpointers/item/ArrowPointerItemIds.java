package com.gaussfff.arrowpointers.item;

public enum ArrowPointerItemId {
    ;

    private final String id;

    ArrowPointerItemId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
