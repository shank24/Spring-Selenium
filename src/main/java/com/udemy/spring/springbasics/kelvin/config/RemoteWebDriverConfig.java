package com.udemy.spring.springbasics.kelvin.config;

import com.udemy.spring.springbasics.kelvin.annotation.LazyConfiguration;
import com.udemy.spring.springbasics.kelvin.annotation.ThreadScopeBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import java.net.URL;


@LazyConfiguration
@Profile("remote")
public class RemoteWebDriverConfig {

    @Value("${selenium.grid.url}")
    private URL url;

    @ThreadScopeBean
    @ConditionalOnProperty(name="browser", havingValue="chrome")
    public WebDriver remoteChromeDriver(){
        return new RemoteWebDriver(this.url, DesiredCapabilities.chrome());
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name="browser", havingValue="firefox")
    public WebDriver remoteFirefoxDriver(){
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }

}
