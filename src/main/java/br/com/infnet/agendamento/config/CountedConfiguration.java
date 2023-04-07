package br.com.infnet.agendamento.config;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CountedConfiguration {
    @Bean
    CountedAspect coutedAspect(MeterRegistry registry){
        return new CountedAspect(registry);
    }
}
