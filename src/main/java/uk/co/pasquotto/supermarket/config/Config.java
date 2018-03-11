package uk.co.pasquotto.supermarket.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${rules.file:file.drl}")
    private String ruleFile;

    @Value("${rules.file.fromClasspath:false}")
    private boolean fileFromClasspath = false;

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        Resource resource = null;
        if(fileFromClasspath) {
            resource = ResourceFactory.newClassPathResource(ruleFile);
        } else {
            resource = ResourceFactory.newFileResource(ruleFile);
        }
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(resource);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
