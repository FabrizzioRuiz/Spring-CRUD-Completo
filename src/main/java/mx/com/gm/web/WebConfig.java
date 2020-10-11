
package mx.com.gm.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author fabri
 */

@Configuration
public class WebConfig implements WebMvcConfigurer{
    /**
     * Metodo que especifica el idioma con el que vamos a trabajar
     * @return 
     */
    @Bean
    public LocaleResolver localeResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
    /**
     * Metodo para que podamos cambiar de idioma de forma dinamica
     * @return 
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    
    /**
     * Metodo para mapear path sin necesidad de pasar por el controlador
     * Mapping de la url del path por deafult.
     * 
     * @param registro 
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        //Agregarmos el path por defult(raiz) y le ponemos de nombre index
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
    
}
