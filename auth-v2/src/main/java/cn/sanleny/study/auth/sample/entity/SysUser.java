package cn.sanleny.study.auth.sample.entity;

import lombok.Data;

@Data
public class SysUser {

    private String name;

    private String password;

    private String userId;

    private String clientId;

    private String salt;

    private String email;

    private String mobile;

    private Byte status;

    private Long deptId;
    
    private String deptName;
    
    private Byte delFlag;
    
    private String roleNames;
    


}