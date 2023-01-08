package homevision.delmoralcristian.houses.config.retry;

import homevision.delmoralcristian.houses.exceptions.InternalServerErrorException;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.UniformRandomBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfiguration {

    @Value("${retry.max.attempts}")
    private Integer maxRetryAttempts;

    @Bean
    public RetryTemplate createRetryTemplate() {
        var simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(maxRetryAttempts);
        var uniformRandomBackOffPolicy = new UniformRandomBackOffPolicy();
        uniformRandomBackOffPolicy.setMinBackOffPeriod(5001);
        uniformRandomBackOffPolicy.setMaxBackOffPeriod(30001);
        return RetryTemplate.builder()
                .customPolicy(simpleRetryPolicy)
                .customBackoff(uniformRandomBackOffPolicy)
                .build();
    }
}
