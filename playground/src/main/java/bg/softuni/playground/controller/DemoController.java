package bg.softuni.playground.controller;

import bg.softuni.playground.dto.LabelDto;
import bg.softuni.playground.dto.ShampooDto;
import bg.softuni.playground.service.LabelService;
import bg.softuni.playground.service.ShampooService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final LabelService labelService;
    private final ShampooService shampooService;

    @PostMapping("/create-label")
    public ResponseEntity<LabelDto> createLabel(@RequestBody LabelDto dto) {
        return ResponseEntity.ok(labelService.create(dto));
    }

    @PostMapping("/create-shampoo")
    public ResponseEntity<ShampooDto> createLabel(@RequestBody ShampooDto dto) {
        return ResponseEntity.ok(shampooService.createShampoo(dto));
    }

    @GetMapping("/label/{id}")
    public ResponseEntity<LabelDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(labelService.getById(id));
    }

}
