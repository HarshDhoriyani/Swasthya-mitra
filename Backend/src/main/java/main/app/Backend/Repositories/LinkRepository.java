package main.app.Backend.Repositories;

import main.app.Backend.Entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity,Long> {
}
