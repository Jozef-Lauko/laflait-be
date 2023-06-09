package sk.umb.fpv.laflait.theses.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;
import sk.umb.fpv.laflait.theses.persistance.repository.ThesesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThesesService {

    private final ThesesRepository thesesRepository;

    public ThesesService(ThesesRepository thesesRepository) {
        this.thesesRepository = thesesRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<ThesesDetailDTO> getAllTheses() {
        return mapToDtoList(thesesRepository.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ThesesDetailDTO getThesisByID(Long id) {

        return mapToDto(getThesisEntityByID(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    private ThesesEntity getThesisEntityByID(Long id) {
        Optional<ThesesEntity> entity = thesesRepository.findById(id);

        if(entity.isEmpty()) {
            throw new IllegalArgumentException("Thesis not found. ID: " + id);
        }

        return entity.get();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updateThesis(Long thesisId, ThesesRequestDTO thesesRequestDTO) {
        ThesesEntity entity = getThesisEntityByID(thesisId);

        if(!Strings.isEmpty(thesesRequestDTO.getTitle())) {
            entity.setTitle(thesesRequestDTO.getTitle());
        }

        if(!Strings.isEmpty(thesesRequestDTO.getDescription())) {
            entity.setDescription(thesesRequestDTO.getDescription());
        }

        thesesRepository.save(entity);
    }

    public List<ThesesDetailDTO> mapToDtoList(Iterable<ThesesEntity> thesesEntities) {
        List<ThesesDetailDTO> theses = new ArrayList<>();

        thesesEntities.forEach(thesesEntity -> {
            ThesesDetailDTO dto = mapToDto(thesesEntity);
            theses.add(dto);
        });

        return theses;
    }

    private ThesesDetailDTO mapToDto(ThesesEntity thesisEntity) {
        ThesesDetailDTO dto = new ThesesDetailDTO();
        dto.setId(thesisEntity.getId());
        dto.setTitle(thesisEntity.getTitle());
        dto.setDescription(thesisEntity.getDescription());

        return dto;
    }

}
