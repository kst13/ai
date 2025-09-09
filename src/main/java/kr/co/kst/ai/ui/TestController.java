package kr.co.kst.ai.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("text")
    public ResponseEntity<String> text() {
        return ResponseEntity.ok("test text");
    }
}
