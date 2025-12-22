package com.ksht.troop2605.scout_finance.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ksht.troop2605.scout_finance.entity.ExpenseClaim;
import com.ksht.troop2605.scout_finance.repository.ExpenseClaimRepository;

import jakarta.validation.Valid;
import lombok.Data;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
@Data
public class ExpenseController {

    private final ExpenseClaimRepository repo;

    public ExpenseController(ExpenseClaimRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ExpenseClaim submit(@RequestBody @Valid ExpenseClaim claim) {
        claim.setStatus(ExpenseClaim.Status.SUBMITTED);
        claim.setSubmittedAt(LocalDateTime.now());
        return repo.save(claim);
    }

    @GetMapping
    public List<ExpenseClaim> all() {
        return repo.findAll();
    }

    @PostMapping("/{id}/approve")
    public ExpenseClaim approve(@PathVariable Long id) {
        ExpenseClaim c = repo.findById(id).orElseThrow();
        c.setStatus(ExpenseClaim.Status.APPROVED);
        return repo.save(c);
    }

    @PostMapping("/{id}/paid")
    public ExpenseClaim paid(@PathVariable Long id) {
        ExpenseClaim c = repo.findById(id).orElseThrow();
        c.setStatus(ExpenseClaim.Status.PAID);
        return repo.save(c);
    }

    @PostMapping("/{id}/upload")
public ResponseEntity<?> upload(
        @PathVariable Long id,
        @RequestParam("file") MultipartFile file
) throws IOException {

    Path dir = Paths.get("uploads/" + id);
    Files.createDirectories(dir);

    Files.copy(file.getInputStream(),
            dir.resolve(file.getOriginalFilename()),
            StandardCopyOption.REPLACE_EXISTING);

    return ResponseEntity.ok().build();
}

}
