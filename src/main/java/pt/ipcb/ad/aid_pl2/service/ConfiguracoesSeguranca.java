package pt.ipcb.ad.aid_pl2.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // (1) Ativa a configuração de segurança web do Spring Security
public class ConfiguracoesSeguranca {

    @Bean // (2) Define o bean da cadeia de filtros de segurança
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // (3) Corrigido o `throws`
        http
                .authorizeHttpRequests(autorizacao -> autorizacao
                        .anyRequest().authenticated() // Toda requisição precisa estar autenticada
                )
                .httpBasic(); // Utiliza autenticação HTTP básica
        return http.build(); // Retorna a cadeia de filtros construída
    }
}
