package com.ksht.troop2605.scout_finance.controller;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
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
