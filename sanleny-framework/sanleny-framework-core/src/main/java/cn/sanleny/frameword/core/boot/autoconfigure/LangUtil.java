package cn.sanleny.frameword.core.boot.autoconfigure;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 国际化工具
 * @Author: LG
 * @Date: 2019-06-12
 * @Version: 1.0
 */
@Configuration
public class LangUtil {

    private static MessageSource messageSource;

    public LangUtil(MessageSource messageSource) {
        LangUtil.messageSource = messageSource;
    }

    public static String get(String code) {
        return get(code, null,"", LocaleContextHolder.getLocale());
    }

    public static String get(String code,Object[] args){
        return get(code, args, "", LocaleContextHolder.getLocale());
    }

    public static String get(String code,String defaultMessage){
        return get(code,null,defaultMessage, LocaleContextHolder.getLocale());
    }

    public String get(String code,Locale locale){
        return get(code,null,"",locale);
    }

    public static String get(String code,Object[] args,String defaultMessage){
        return get(code,args, defaultMessage, LocaleContextHolder.getLocale());
    }

    private static String get(String code, Object[] args,String defaultMessage, Locale locale){
        return messageSource.getMessage(code, args, defaultMessage,locale);
    }

}
