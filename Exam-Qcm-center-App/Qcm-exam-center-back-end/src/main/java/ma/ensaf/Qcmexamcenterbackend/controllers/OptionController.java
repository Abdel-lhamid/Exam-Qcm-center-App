package ma.ensaf.Qcmexamcenterbackend.controllers;


import ma.ensaf.Qcmexamcenterbackend.dtos.OptionDto;
import ma.ensaf.Qcmexamcenterbackend.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    // Create a new option for a question
    @PostMapping
    public ResponseEntity<OptionDto> createOption(@RequestBody OptionDto optionDto) {
        OptionDto createdOption = optionService.createOption(optionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOption);
    }

    // Retrieve all options for a specific question
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<OptionDto>> getOptionsForQuestion(@PathVariable String questionId) {
        List<OptionDto> options = optionService.getAllOptionsByQuestion(questionId);
        return ResponseEntity.ok(options);
    }

    // Retrieve a specific option by ID
    @GetMapping("/{optionId}")
    public ResponseEntity<OptionDto> getOptionById(@PathVariable String optionId) {
        OptionDto option = optionService.getOptionByOptionId(optionId);
        return ResponseEntity.ok(option);
    }

    // Update a specific option
    @PutMapping("/{optionId}")
    public ResponseEntity<OptionDto> updateOption(@PathVariable String optionId, @RequestBody OptionDto optionDto) {
        OptionDto updatedOption = optionService.updateOption(optionId, optionDto);
        return ResponseEntity.ok(updatedOption);
    }

    // Delete a specific option
    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable String optionId) {
        optionService.deleteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}
