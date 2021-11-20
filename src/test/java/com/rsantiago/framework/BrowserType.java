package com.rsantiago.framework;

public enum BrowserType {
    CHROME,
    EDGE,
    FIREFOX;

    public boolean isEqualTo(String name) {
        return name.equalsIgnoreCase(this.name());
    }
}
