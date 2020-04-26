package br.com.mySpringBoot.api.repository;

import br.com.mySpringBoot.api.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
