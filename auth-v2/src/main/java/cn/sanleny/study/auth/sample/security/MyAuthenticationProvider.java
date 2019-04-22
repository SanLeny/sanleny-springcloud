package cn.sanleny.study.auth.sample.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * UserDetailsService
     * @param userDetailsService
     */
    public MyAuthenticationProvider(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    /**
     * 密码验证
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        System.out.println(">>>presentedPassword:"+presentedPassword);
//        String salt = ((JwtUserDetails) userDetails).getSalt();
        //if (!new PasswordEncoder(salt).matches(userDetails.getPassword(), presentedPassword)) {
        // 这里写覆写密码验证逻辑
//        if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
//            logger.debug("Authentication failed: password does not match stored value");
//            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//        }
    }


}
