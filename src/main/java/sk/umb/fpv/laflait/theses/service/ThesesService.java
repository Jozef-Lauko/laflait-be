package sk.umb.fpv.laflait.theses.service;

import org.springframework.stereotype.Service;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;
import sk.umb.fpv.laflait.theses.persistance.repository.ThesesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThesesService {

    private final ThesesRepository thesesRepository;

    public ThesesService(ThesesRepository thesesRepository) {
        this.thesesRepository = thesesRepository;
    }

    public List<ThesesDetailDTO> getAllTheses() {
        return mapToDtoList(thesesRepository.findAll());
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
        dto.setThesis(thesisEntity.getThesis());
        dto.setDescription(thesisEntity.getDescription());

        return dto;
    }
}
