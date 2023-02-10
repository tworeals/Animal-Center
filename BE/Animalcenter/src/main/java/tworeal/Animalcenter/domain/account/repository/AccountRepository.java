package tworeal.Animalcenter.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tworeal.Animalcenter.domain.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
