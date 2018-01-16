package com.elextec.bi.entity;

/**
 * Created by js_gg on 2018/1/11.
 */
public class BiRoleDataPermission {
    private String id;
    private String roleId;
    private String dataPermissionId;
    private String config;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDataPermissionId() {
        return dataPermissionId;
    }

    public void setDataPermissionId(String dataPermissionId) {
        this.dataPermissionId = dataPermissionId;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
