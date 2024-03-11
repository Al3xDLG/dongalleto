package mx.com.dongalleto.app.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class ViewController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/")
    public String index() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/index.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}