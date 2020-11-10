package cn.sanleny.framework.auth.common.constant;

/**
 * @Author: LG
 * @Date: 2020-09-22
 * @Version: 1.0
 **/
public interface SecurityConstants {

    /**
     * {bcrypt} 加密的特征码
     */
    String BCRYPT = "{bcrypt}";

    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";

    /**
     * 授权类型: password
     */
    String GRANT_TYPE_PASSWORD = "password";

    /**
     * 授权类型: client_credentials
     */
    String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 授权类型: refresh_token
     */
    String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    /**
     * 用户ID字段
     */
    String TOKEN_USER_ID = "userId";

    /**
     * 用户名字段
     */
    String TOKEN_USERNAME = "username";

    /**
     * 租户ID字段
     */
    String TOKEN_TENANT_ID = "tenantId";

}
