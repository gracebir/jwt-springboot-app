package com.grace.jwt_authentication.user;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CUSTOMER(Arrays.asList(Permissions.READ_ALL_PRODUCTS)),
    ADMIN(Arrays.asList(Permissions.READ_ALL_PRODUCTS, Permissions.SAVE_ONE_PRODUCT));

   private List<Permissions> permissions;

    Role(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}
